package com.hmx.management.controller.user;


import com.hmx.common.util.Result;
import com.hmx.user.dao.RoleMapper;
import com.hmx.user.dao.UserModelMapper;
import com.hmx.user.entity.po.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping(value = "/system/userManage")
public class UserModelController {
    @Autowired
    private UserModelMapper userDao;

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private UserModelMapper userService;
    
    /**
     *@Author: shi
     *@Description: 用户管理初始化
     *@param:
     *@return 
     *@Date: 17:09 2018/7/20
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/system/user/list");
        return mv;
    }

    /**
     *@Author: shi
     *@Description: 用户管理编辑
     *@param: request
     *@return 
     *@Date: 17:09 2018/7/20
     */
    @RequestMapping(value = "/eidt")
    public ModelAndView eidt(HttpServletRequest request, Integer id){
        ModelAndView mv = new ModelAndView();
        UserModel userModelLogin = (UserModel) request.getSession().getAttribute("userInfo");
//        if(userModelLogin.getOrganizationId() != null){
//            mv.addObject("organizationzModel",userService.getSubInOrganization(userModelLogin.getOrganizationId()));
//        }
        mv.addObject("roleModel",roleDao.findAll());
        mv.addObject("user",id == null ? new UserModel() : userDao.selectByPrimaryKey(id));
        mv.setViewName("/system/user/eidt");
        return mv;
    }

    /**
     *@Author: shi
     *@Description: 条件获取所有用户
     *@param: userModel pageable request
     *@return 
     *@Date: 17:10 2018/7/20
     */
    @RequestMapping(value = "/getLists")
    @ResponseBody
    public Map<String, Object> getLists(UserModel userModel, Pageable pageable, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        UserModel userModelLogin = (UserModel) request.getSession().getAttribute("userInfo");
        if(userModelLogin.getOrganizationId() != null){
            userModel.setOrganizationId(userModelLogin.getOrganizationId());
            List<UserModel> modelPage = userService.findAll(pageable.getPageNumber(),pageable.getPageSize());
            result.put("rows", modelPage);
            result.put("total", modelPage.size());
        }
        return result;
    }

    /**
     *@Author: shi
     *@Description: 用户禁用/启用
     *@param: userId valid
     *@return 
     *@Date: 17:11 2018/7/20
     */
    @RequestMapping(value = "/isValid")
    @ResponseBody
    public Result<Object> isValid(Integer userId, Boolean isValid){
        Result<Object> result = new Result<>();
        result.setStatus(10000);
//        userDao.updateById(isValid,userId);
        return result;
    }

    /**
     *@Author: shi
     *@Description:  新增或修改用户
     *@param: userData
     *@return 
     *@Date: 10:44 2018/7/23
     */
//    @RequestMapping(value = "/addOrUpdateUser")
//    @ResponseBody
//    public Result<Object> addOrUpdateUser(UserData userData){
//        return userService.addOrUpdateUser(userData);
//    }

    /**
     *@Author: shi
     *@Description:  新增或修改用户
     *@param: username name cellPhone validation
     *@return
     *@Date: 10:44 2018/7/23
     */
//    @RequestMapping(value = "/updateUser")
//    @ResponseBody
//    public Result<Object> updateUser(String username,String name,String cellPhone,String validation){
//        return userService.updateUser(username,name,cellPhone,validation);
//    }

    /**
     *@Author: shi
     *@Description:  获取验证码
     *@param: userData
     *@return
     *@Date: 10:44 2018/7/25
     */
//    @RequestMapping(value = "/getValidation")
//    @ResponseBody
//    public Result<Object> getValidation(HttpServletRequest request , String cellPhone){
//        UserModel userModelLogin = (UserModel) request.getSession().getAttribute("userInfo");
//        logger.info(userModelLogin.getUsername()+"---获取验证码："+cellPhone);
//        return userService.getValidation(cellPhone);
//    }

    /**
     *@Author: shi
     *@Description:  修改密码
     *@param: userData
     *@return
     *@Date: 10:44 2018/7/25
     */
//    @RequestMapping(value = "/modifyPassword")
//    @ResponseBody
//    public Result<Object> modifyPassword(HttpServletRequest request , Integer userId, Boolean isReset, String newPassword){
//        UserModel userModelLogin = (UserModel) request.getSession().getAttribute("userInfo");
//        logger.info(userModelLogin.getUsername()+"---修改密码："+userId+"________"+newPassword);
//        return userService.modifyPassword(userId,isReset,newPassword);
//    }

}
