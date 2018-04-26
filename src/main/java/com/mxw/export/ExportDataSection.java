package com.mxw.export;

import java.io.Serializable;
import java.util.List;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportDataSection<T extends ExportData> implements Serializable {
    public ExportDataSection() {
    }

    public ExportDataSection(List<T> dataList, long order) {
        this.dataList = dataList;
        this.order = order;
    }

    private List<T> dataList;
    private long order;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
