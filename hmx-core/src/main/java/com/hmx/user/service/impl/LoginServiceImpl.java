package com.hmx.user.service.impl;


import com.hmx.data.LoginButtonData;
import com.hmx.user.dao.PermissionMapper;
import com.hmx.user.entity.po.Permission;
import com.hmx.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * @author shi
 * @create 2018-07-04  21:43
 * @description
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PermissionMapper permissionDao;

    @Override
    public List<LoginButtonData> loginButton(int userId) {
        List<LoginButtonData> loginButtonDatas = new ArrayList<>();
        //查询一级菜单按钮
        List<Permission> models = permissionDao.findPermissionButtonByUserId(userId,0);
        if(models != null && models.size() > 0){
            for(Permission model : models){
                LoginButtonData data = new LoginButtonData();
                data.setId(model.getId());
                data.setButtonName(model.getName());
                data.setUrl(model.getUrl());
                data.setIcon(model.getIcon());
                //查询菜单下的子按钮
                data.setPermissionModels(permissionDao.findPermissionButtonByUserId(userId,model.getId()));
                loginButtonDatas.add(data);
            }
        }
        return loginButtonDatas;
    }

    @Override
    public Map<String, Integer> loadHomeStatistics() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        return map;

    }
}
