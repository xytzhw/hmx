package com.hmx.category.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.hmx.category.dto.HmxCategoryDto;
import com.hmx.category.entity.HmxCategory;
import com.hmx.category.service.HmxCategoryService;
import com.hmx.utils.result.Config;
import com.hmx.utils.result.PageBean;
import com.hmx.utils.result.ResultBean;

import org.springframework.ui.Model;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private HmxCategoryService hmxCategoryService;

	/**
	 * 首页分类添加
	 * @param hmxcategoryDto
	 * @param model
	 * @return
	 */
	@PostMapping("/add")
	public String categoryAdd(HmxCategoryDto hmxcategoryDto,Model model){
		ResultBean resultBean = new ResultBean();
		boolean flag=true;
		if(StringUtils.isEmpty(hmxcategoryDto.getCategoryName())){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY);
			resultBean.setContent("分类名称不能为空");
			flag=false;
		}
		if(flag){
			Map<String,Object> resultMap = hmxCategoryService.categoryAdd(hmxcategoryDto);
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
	 * 首页分类信息查找
	 * @return
	 */
	@GetMapping("/{id}")
	public String categoryInfo(@PathVariable(name="id",required=true) Integer categoryId,Model model){
		ResultBean resultBean = new ResultBean();
		HmxCategory hmxCategory = hmxCategoryService.info(categoryId);
		if(hmxCategory == null){
			resultBean.setCode(Config.FAIL_CODE);
			resultBean.setContent("查询分类信息失败");
		}else{
			resultBean.setCode(Config.SUCCESS_CODE);
			resultBean.setContent("查询分类信息成功");
		}
		resultBean.put("hmxCategory", hmxCategory);
		model.addAttribute("resultBean", resultBean);
		return "hello";
	}
	/**
	 * 分类信息更新
	 * @param model
	 * @return
	 */
	@PostMapping("/edit")
	public String categoryUpdate(HmxCategoryDto hmxcategoryDto,Model model){
		ResultBean resultBean = new ResultBean();
		boolean flag=true;
		if(hmxcategoryDto.getCategoryId() == null){
			resultBean.setCode(Config.FAIL_FIELD_EMPTY);
			resultBean.setContent("分类编号不能为空");
			flag=false;
		}
		if(flag){
			Map<String,Object> resultMap = hmxCategoryService.categoryUpdate(hmxcategoryDto);
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
	 * 分类列表信息
	 * @param hmxcategoryDto
	 * @param page
	 * @param model
	 * @return
	 */
	@PostMapping("/categoryTable")
	public String categoryTable(HmxCategoryDto hmxcategoryDto,PageBean<Map<String,Object>> page,Model model){
		ResultBean resultBean = new ResultBean();
		page = hmxCategoryService.selectCategoryTable(page, hmxcategoryDto);
		List<Map<String,Object>> list = page.getPage();
		if(list == null || list.size() <= 0){
			if(page.getPageNum() == 1){
				resultBean.setCode(Config.CONTENT_NULL).setContent("暂无数据");
   	    	}
   	    	else{
   	    		resultBean.setCode(Config.PAGE_NULL).setContent("没有更多数据了");
   	    	}
		}else{
			resultBean.setCode(Config.SUCCESS_CODE).setContent("查询分类列表成功");
		}
		resultBean.put("categoryPage", page);
		model.addAttribute("resultBean", resultBean);
		return "hello";
	}
}
