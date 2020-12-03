package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author jun
 * @date 2020/11/28 - 16:19
 */
public interface ActivityDao {
    int save(Activity activity);


    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getDataListByCondition(Map<String, Object> map);

    int delete(String[] ids);
}
