package com.hmx.category.dto;

import com.hmx.category.entity.HmxCategory;
/**
 * Dto entity
 */
public class HmxCategoryDto extends HmxCategory {

	private Integer limit;

	private String orderByClause;

	public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    } 
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

}