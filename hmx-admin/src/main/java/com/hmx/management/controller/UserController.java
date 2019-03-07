package com.hmx.management.controller;

import com.hmx.management.entity.User;
import com.hmx.management.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class UserController
{
    @Resource
    private UserMapper userMapper;

    @RequestMapping(value="/getUser",method = RequestMethod.GET)
    public String getUser(@RequestParam("id") Integer id) {
        User user=userMapper.getUser(id);
        System.out.println(user);
        return "hello";
    }


}
