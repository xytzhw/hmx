package com.hmx.category.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmx.category.service.HmxCategoryContentService;
import com.hmx.utils.result.Config;
import com.hmx.utils.result.ResultBean;

@RestController
@RequestMapping("/categoryContent")
public class CategoryContentController {
	
	@Autowired
	private HmxCategoryContentService hmxCategoryContentService;

	@GetMapping("/{id}")
	public ResultBean getCategoryContentById(@PathVariable(name="id",required=true) Integer categoryContentId){
		if(categoryContentId == null){
			return new ResultBean().setCode(Config.FAIL_FIELD_EMPTY).setContent("内容编号不能为空");
		}
		Map<String,Object> resultMap = hmxCategoryContentService.selectContentInfoByContentId(categoryContentId);
		if(resultMap == null){
			return new ResultBean().setCode(Config.FAIL_CODE).setContent("没有查找到内容详情信息");
		}
		return new ResultBean().setCode(Config.SUCCESS_CODE).put("categoryContentInfo", resultMap).setContent("获取内容详情成功");
	}
}
