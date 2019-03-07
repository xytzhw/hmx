package com.hmx.management.mapper;

import com.hmx.management.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper
{
    @Select("SELECT * FROM hmx_user")
    User getUser(Integer id);
}
