package com.hmx.category.dao;


import com.hmx.category.entity.HmxCategoryContent;
import com.hmx.category.entity.HmxCategoryContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Dao interface
 */
public interface HmxCategoryContentMapper{

    int countByExample(HmxCategoryContentExample example);

    int deleteByExample(HmxCategoryContentExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(HmxCategoryContent record);

    int insertSelective(HmxCategoryContent record);

    List<HmxCategoryContent> selectByExample(HmxCategoryContentExample example);

    HmxCategoryContent selectByPrimaryKey(Integer hmxCategoryContentId);

    int updateByExampleSelective(@Param("record") HmxCategoryContent record, @Param("example") HmxCategoryContentExample example);

    int updateByExample(@Param("record") HmxCategoryContent record, @Param("example") HmxCategoryContentExample example);

    int updateByPrimaryKeySelective(HmxCategoryContent record);

    int updateByPrimaryKey(HmxCategoryContent record);

}