package com.bjpowernode.crm.vo;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;

/**
 * @author jun
 * @date 2020/11/30 - 15:56
 */
public class PaginationVo<T> {
    private int total;
    private List<T> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
