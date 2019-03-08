package com.hmx.user.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hmx.user.entity.HmxUser;
import com.hmx.user.service.HmxUserService;
import com.hmx.utils.logger.LogHelper;
import com.hmx.utils.random.RandomHelper;
import com.hmx.utils.result.Config;
import com.hmx.utils.result.ResultBean;
import com.hmx.utils.secret.MD5Util;
import com.hmx.utils.sms.SMSSendOut;
import com.hmx.verifylog.entity.HmxVerifylog;
import com.hmx.verifylog.service.HmxVerifylogService;


@RestController
@ResponseBody
public class UserController {

	@Autowired
	private HmxUserService hmxUserService;
	@Autowired
	private SMSSendOut SMSSendOut;
	@Autowired
	private HmxVerifylogService hmxVerifylogService;
	
	@GetMapping("/user/{id}")
	public ResultBean getUserInfo(@PathVariable Integer id){
		HmxUser user = hmxUserService.info(id);
		return new ResultBean().put("user", user);
	}
	/**
	 * 注册用户
	 * @param hmxUser
	 * @return
	 */
	@PostMapping("/user/add")
	public ResultBean addUser(@ModelAttribute HmxUser hmxUser){
		String password = hmxUser.getPassword();
		String userPhone = hmxUser.getUserPhone();
		if(StringUtils.isEmpty(userPhone)){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("手机号不能为空");
		}
		if(StringUtils.isEmpty(password)){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("密码不能为空");
		}
		hmxUser.setPassword(MD5Util.encode(password));
		Boolean flag = hmxUserService.insert(hmxUser);
		if(!flag){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("注册用户失败");
		}
		return new ResultBean().setCode(Config.SUCCESS_CODE).setContent("注册用户成功");
	}
	/**
	 * 发送验证码
	 * @param hmxUser
	 * @return
	 */
	@PostMapping("/user/send")
	public ResultBean smsSend(@ModelAttribute HmxUser hmxUser){
		String userPhone = hmxUser.getUserPhone();
		if(StringUtils.isEmpty(userPhone)){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("手机号不能为空");
		}
		String code = RandomHelper.getRandomNum(6);
		try {
			boolean flag = SMSSendOut.SMSSending(userPhone, code);
			if(!flag){
				return new ResultBean().setCode(Config.FAIL_CODE).setContent("发送验证码失败");
			}
			HmxVerifylog hmxVerifylog = new HmxVerifylog();
			hmxVerifylog.setAddTime(new Date());
			hmxVerifylog.setVerifyCode(code);
			hmxVerifylog.setVerifyObject(userPhone);
			hmxVerifylog.setVerifyType(0);
			flag = hmxVerifylogService.insert(hmxVerifylog);
			return new ResultBean().setCode(Config.SUCCESS_CODE).setContent("发送验证码成功");
		} catch (Exception e) {
			LogHelper.logger().error("操作失败", e);
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("发送验证码失败" + e.getMessage());
		}
	}
}
