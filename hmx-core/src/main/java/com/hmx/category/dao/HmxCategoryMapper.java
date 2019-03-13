package com.hmx.category.dao;


import com.hmx.category.entity.HmxCategory;
import com.hmx.category.entity.HmxCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Dao interface
 */
public interface HmxCategoryMapper{

    int countByExample(HmxCategoryExample example);

    int deleteByExample(HmxCategoryExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(HmxCategory record);

    int insertSelective(HmxCategory record);

    List<HmxCategory> selectByExample(HmxCategoryExample example);

    HmxCategory selectByPrimaryKey(Integer hmxCategoryId);

    int updateByExampleSelective(@Param("record") HmxCategory record, @Param("example") HmxCategoryExample example);

    int updateByExample(@Param("record") HmxCategory record, @Param("example") HmxCategoryExample example);

    int updateByPrimaryKeySelective(HmxCategory record);

    int updateByPrimaryKey(HmxCategory record);

}