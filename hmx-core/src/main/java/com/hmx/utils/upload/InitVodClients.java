package com.hmx.utils.upload;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
/**
 * 账号AccessKey初始化
 * 如国内请使用 cn-shanghai
 * @author liY
 *
 */
public class InitVodClients {
	
	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
	    String regionId = "cn-shanghai";  // 点播服务接入区域
	    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
	    DefaultAcsClient client = new DefaultAcsClient(profile);
	    return client;
	}

}
