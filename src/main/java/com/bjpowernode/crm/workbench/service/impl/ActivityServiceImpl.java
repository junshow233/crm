package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.dao.ActivityDao;
import com.bjpowernode.crm.workbench.dao.ActivityRemarkDao;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

/**
 * @author jun
 * @date 2020/11/28 - 16:24
 */
public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);

    public Boolean save(Activity activity) {
        Boolean flag = true;
        int count = activityDao.save(activity);

        if (count!=1){
            flag=false;
        }
        return flag;
    }

    public PaginationVo<Activity> pageList(Map<String,Object> map) {

        int total = activityDao.getTotalByCondition(map);

        List<Activity> activityList = activityDao.getDataListByCondition(map);

        PaginationVo<Activity> vo = new PaginationVo<Activity>();

        vo.setTotal(total);
        vo.setDataList(activityList);
        return vo;
    }

    public Boolean delete(String[] ids) {
        Boolean flag = true;

        int count = activityRemarkDao.getCountByAids(ids);

        int count2 = activityRemarkDao.deleteByAids(ids);

        int count3 = activityDao.delete(ids);

        if (count!=count2){
            flag=false;
        }
        if (count3!=ids.length){
            flag=false;
        }

        return flag;
    }
}
