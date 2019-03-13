package com.hmx.category.dto;

import com.hmx.category.entity.HmxCategoryContent;
/**
 * Dto entity
 */
public class HmxCategoryContentDto extends HmxCategoryContent {

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