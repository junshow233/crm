package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVo;
import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.Map;

/**
 * @author jun
 * @date 2020/11/28 - 16:23
 */
public interface ActivityService {
    Boolean save(Activity activity);

    PaginationVo<Activity> pageList(Map<String,Object> map);

    Boolean delete(String[] ids);
}
