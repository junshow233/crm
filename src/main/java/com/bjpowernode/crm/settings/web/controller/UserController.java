package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jun
 * @date 2020/11/27 - 13:40
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("用户管理模块");
        String path = request.getServletPath();
        System.out.println(path);

        if ("/settings/user/login.do".equals(path)){
            login(request,response);

        }else if ("/settings/user/xxx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request,HttpServletResponse response){

        System.out.println("验证登录");

        String loginAct = request.getParameter("loginAct");
        String loginPsw = request.getParameter("loginPsw");
        loginPsw = MD5Util.getMD5(loginPsw);

        String ip = request.getRemoteAddr();

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        User user = null;
        try {

            user = us.login(loginAct,loginPsw,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);

        } catch (LoginException e) {
            e.printStackTrace();

            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }


    }
}
