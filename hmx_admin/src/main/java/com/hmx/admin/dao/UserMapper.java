package com.hmx.admin.dao;

import com.hmx.admin.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper
{
    @Select("SELECT * FROM hmx_user")
    User getUser(Integer id);
}
