package com.hmx.category.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.hmx.category.dto.HmxCategoryContentDto;
import com.hmx.category.service.HmxCategoryContentService;
import com.hmx.utils.result.Config;
import com.hmx.utils.result.PageBean;
import com.hmx.utils.result.ResultBean;

@RestController
@RequestMapping("/categoryContent")
public class CategoryContentController {
	
	@Autowired
	private HmxCategoryContentService hmxCategoryContentService;

	/**
	 * 分类内容添加
	 * @param hmxcategoryDto
	 * @param model
	 * @return
	 */
	@PostMapping("/add")
	public String categoryAdd(HmxCategoryContentDto hmxCategoryContentDto,Model model){
		ResultBean resultBean = new ResultBean();
		boolean flag=true;
		if(StringUtils.isEmpty(hmxCategoryContentDto.getCategoryTitle())){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY).setContent("内容标题不能为空");
			flag=false;
		}
		if(StringUtils.isEmpty(hmxCategoryContentDto.getCategoryContent())){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY).setContent("内容不能为空");
			flag=false;
		}
		if(hmxCategoryContentDto.getCategoryId() == null){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY).setContent("关联首页分类不能为空");
			flag=false;
		}
		if(flag){
			Map<String,Object> resultMap = hmxCategoryContentService.categoryContentAdd(hmxCategoryContentDto);
			flag=Boolean.parseBoolean(resultMap.get("flag").toString());
			if(!flag){
				resultBean.setCode(Config.FAIL_CODE);
			}else{
				resultBean.setCode(Config.SUCCESS_CODE);
			}
			resultBean.setContent(resultMap.get("content").toString());
		}
		model.addAttribute("resultBean", resultBean);
		return "hello";
	}
	
	/**
	 * 分类内容编辑
	 * @param hmxcategoryDto
	 * @param model
	 * @return
	 */
	@PostMapping("/edit")
	public String categoryUpdate(HmxCategoryContentDto hmxCategoryContentDto,Model model){
		ResultBean resultBean = new ResultBean();
		boolean flag=true;
		if(hmxCategoryContentDto.getCategoryContentId() == null){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY).setContent("内容编号不能为空");
			flag=false;
		}
		if(flag){
			Map<String,Object> resultMap = hmxCategoryContentService.categoryContentUpdate(hmxCategoryContentDto);
			flag=Boolean.parseBoolean(resultMap.get("flag").toString());
			if(!flag){
				resultBean.setCode(Config.FAIL_CODE);
			}else{
				resultBean.setCode(Config.SUCCESS_CODE);
			}
			resultBean.setContent(resultMap.get("content").toString());
		}
		model.addAttribute("resultBean", resultBean);
		return "hello";
	}
	/**
	 * 内容详情查询
	 * @param categoryContentId
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public String categoryContentInfo(@PathVariable(name="id",required=true) Integer categoryContentId,Model model){
		ResultBean resultBean = new ResultBean();
		boolean flag=true;
		if(categoryContentId == null){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY).setContent("内容编号不能为空");
			flag=false;
		}
		if(flag){
			Map<String,Object> resultMap = hmxCategoryContentService.selectCategoryContentById(categoryContentId);
			if(resultMap == null){
				resultBean.setCode(Config.FAIL_CODE).setContent("查询内容详情失败");
			}else{
				resultBean.setCode(Config.SUCCESS_CODE).setContent("查询内容详情成功");
			}
			resultBean.put("categoryContentInfo", resultMap);
		}
		model.addAttribute("resultBean", resultBean);
		return "hello";
	}
	
	/**
	 * 内容列表
	 * @param hmxCategoryContentDto
	 * @param page
	 * @param model
	 * @return
	 */
	@GetMapping("/categoryContentTable")
	public Map<String,Object> categoryContentTable(HmxCategoryContentDto hmxCategoryContentDto,PageBean<Map<String,Object>> page,Model model){
		Map<String,Object> map = new HashMap<>();
		ResultBean resultBean = new ResultBean();
		page = hmxCategoryContentService.selectCategoryContentTable(page, hmxCategoryContentDto);
		List<Map<String,Object>> list = page.getPage();
		if(list == null || list.size() <= 0){
			if(page.getPageNum() == 1){
				resultBean.setCode(Config.CONTENT_NULL).setContent("暂无数据");
   	    	}
   	    	else{
   	    		resultBean.setCode(Config.PAGE_NULL).setContent("没有更多数据了");
   	    	}
		}else{
			resultBean.setCode(Config.SUCCESS_CODE).setContent("查询内容列表成功");
		}
		map.put("rows", page.getPage());
		map.put("total", page.getTotalNum());
		return map;
	}
}
