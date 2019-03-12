package com.hmx.user.dao;


import com.hmx.user.entity.HmxUser;
import com.hmx.user.entity.HmxUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Dao interface
 */
public interface HmxUserMapper{

    int countByExample(HmxUserExample example);

    int deleteByExample(HmxUserExample example);

    int deleteByPrimaryKey(Integer configId);

    int insert(HmxUser record);

    int insertSelective(HmxUser record);

    List<HmxUser> selectByExample(HmxUserExample example);

    HmxUser selectByPrimaryKey(Integer hmxUserId);

    int updateByExampleSelective(@Param("record") HmxUser record, @Param("example") HmxUserExample example);

    int updateByExample(@Param("record") HmxUser record, @Param("example") HmxUserExample example);

    int updateByPrimaryKeySelective(HmxUser record);

    int updateByPrimaryKey(HmxUser record);

}