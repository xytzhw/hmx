package com.hmx.utils.upload;

import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.req.*;
import com.aliyun.vod.upload.resp.*;
import com.hmx.utils.logger.LogHelper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UploadVideoDemo {
    //账号AK信息请填写(必选)
	@Value("${accessKeyId}")
    private String accessKeyId;
    //账号AK信息请填写(必选)
	@Value("${accessKeySecret}")
    private String accessKeySecret;
	@Value("${imageType}")
	private String imageType;
	@Value("${imageExt}")
	private String imageExt;

	public boolean imageExt(String ext){
		String[] extArray = imageExt.split(",");
		if(extArray != null && extArray.length>0){
			for(String e : extArray){
				if(ext.equals(e)){
					return true;
				}
			}
		}
		return false;
	}
    /**
     * 图片上传接口，本地文件上传示例
     * 参数参考文档 https://help.aliyun.com/document_detail/55619.html
     *
     * @param accessKeyId
     * @param accessKeySecret
     */
    public Map<String,Object> hmxUploadImageLocalFile(InputStream inputStream,String fileName) {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	resultMap.put("flag", false);
    	resultMap.put("url", null);
    	try {
    		/* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
    		UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
    		request.setImageType(imageType);
    		/* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
    		String[] extArray = fileName.split("\\.");
    		String ext = extArray[extArray.length-1];
    		boolean flag = imageExt(ext);
    		if(!flag){
    			resultMap.put("content", "图片格式不正确，暂时只支持"+imageExt+"格式!");
    			return resultMap;
    		}
    		request.setImageExt(ext);
    		/* 图片标题（可选）长度不超过128个字节，UTF8编码 */
    		//request.setTitle("图片标题");
    		/* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
    		//request.setTags("标签1,标签2");
    		/* 存储区域（可选）*/
    		//request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
    		/* 流式上传时，InputStream为必选，fileName为源文件名称，如:文件名称.png(可选)*/
//    		String fileName = "C:\\Users\\Administrator\\Desktop\\gif\\t010d337c5658f73a5e.jpg";
    		request.setFileName(fileName);
    		request.setInputStream(inputStream);
    		/* 开启默认上传进度回调 */
    		//request.setPrintProgress(false);
    		/* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
    		// request.setProgressListener(new PutObjectProgressListener());
    		UploadImageImpl uploadImage = new UploadImageImpl();
    		UploadImageResponse response = uploadImage.upload(request);
    		LogHelper.logger().info("RequestId=" + response.getRequestId());
    		if (response.isSuccess()) {
    			LogHelper.logger().info("ImageId=" + response.getImageId());
    			LogHelper.logger().info("ImageURL=" + response.getImageURL() + "\n");
    			resultMap.put("flag", true);
    			resultMap.put("url", response.getImageURL());
    			resultMap.put("content", "上传图片成功");
    		} else {
    			LogHelper.logger().info("ErrorCode=" + response.getCode() + "\n");
    			LogHelper.logger().info("ErrorMessage=" + response.getMessage() + "\n");
    			resultMap.put("content", "上传图片失败:"+response.getMessage());
    		}
    		return resultMap;
		} catch (Exception e) {
			resultMap.put("content", "上传图片异常:"+e.getMessage());
			return resultMap;
		}
    	
    }
}