package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.*;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jun
 * @date 2020/11/27 - 13:40
 */
public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("市场活动模块");
        String path = request.getServletPath();
        System.out.println(path);

        if ("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);

        }else if ("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if ("/workbench/activity/pageList.do".equals(path)){
            pageLIst(request,response);
        }else if ("/workbench/activity/delete.do".equals(path)){
            delete(request,response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除市场活动信息的操作");

        String ids[] = request.getParameterValues("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        Boolean flag = activityService.delete(ids);
        PrintJson.printJsonFlag(response,flag);

    }

    private void pageLIst(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场获得信息列表的操作");

        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStar = request.getParameter("pageNo");
        String pageSizeStar = request.getParameter("pageSize");

        int pagNo = Integer.valueOf(pageNoStar);
        int pagSize = Integer.valueOf(pageSizeStar);
        int skipCount = (pagNo-1)*pagSize;

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pagSize);

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        PaginationVo<Activity> vo =  activityService.pageList(map);
        PrintJson.printJsonObj(response,vo);




    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("保存用户信息");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity activity = new Activity();
        activity.setOwner(request.getParameter("owner"));
        activity.setCost(request.getParameter("cost"));
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setDescription(request.getParameter("description"));
        activity.setName(request.getParameter("name"));
        activity.setId(UUIDUtil.getUUID());
        activity.setStartDate(request.getParameter("startDate"));
        activity.setEndDate(request.getParameter("endDate"));
        activity.setCreateBy(((User)request.getSession().getAttribute("user")).getName());

        Boolean flag = as.save(activity);

        PrintJson.printJsonFlag(response,flag);

    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response){

        System.out.println("取用户信息");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> list = us.getUserList();

        PrintJson.printJsonObj(response,list);
    }

}
