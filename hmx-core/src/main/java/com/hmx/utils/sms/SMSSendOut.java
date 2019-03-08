package com.hmx.utils.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSSendOut {
	
	@Value("${SMSSendUrl}")
	private String SMSSendUrl;
	@Value("${SMSUserName}")
	private String SMSUserName;
	@Value("${SMSPassword}")
	private String SMSPassword;
	@Value("${SMSGwid}")
	private String SMSGwid;
	@Value("${SMSVerificationTemplate}")
	private String SMSVerificationTemplate;

	/**
	 * 
	 * @param phones
	 * @param code
	 * @return
	 */
	public boolean SMSSending(String phones,String code){
		//短信内容
		String content = SMSVerificationTemplate.replaceAll("code", code);
		try {
			content = URLEncoder.encode(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//拼接参数
        String postData = "type=send&username="+SMSUserName+"&password="+SMSPassword+"&gwid="+SMSGwid+"&mobile="+phones+"&message="+content+"";
        //发送并把结果赋给result,返回一个XML信息,解析xml 信息判断
        String result=SMSHelper.sendPost(SMSSendUrl, postData);
        if(result.indexOf("success") != -1){
    	    return true;
        }
	    return false;
	}
}
