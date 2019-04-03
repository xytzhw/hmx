package com.hmx.fileupload.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.hmx.utils.result.Config;
import com.hmx.utils.result.ResultBean;
import com.hmx.utils.upload.UploadVideoDemo;

@RestController
@RequestMapping("/movieUpload")
public class movieFileUploadController {
	
	@Autowired
	private UploadVideoDemo uploadVideoDemo;

	@RequestMapping("/upload")
	public ResultBean fileImageUpload(MultipartFile file,String title){
		if(file == null){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("上传视频文件不能为空");
		}
		if(StringUtils.isEmpty(title)){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("上传视频文件标题不能为空");
		}
		try {
			Map<String,Object> resultMap = uploadVideoDemo.hmxUploadVideo(file.getInputStream(), file.getOriginalFilename(),title.trim());
			boolean flag = Boolean.parseBoolean(resultMap.get("flag").toString());
			if(!flag){
				return new ResultBean().setCode(Config.FAIL_CODE).setContent(resultMap.get("content").toString());
			}
			return new ResultBean().setCode(Config.SUCCESS_CODE).setContent("上传图片成功").put("url", resultMap.get("url")).setContent(resultMap.get("content").toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("图片上传异常");
		}
	}
}
