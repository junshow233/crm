package com.bjpowernode.crm.workbench.dao;

/**
 * @author jun
 * @date 2020/11/29 - 15:12
 */
public interface ActivityRemarkDao {
    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);
}
