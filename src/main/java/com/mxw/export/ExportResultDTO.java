package com.mxw.export;

import java.io.Serializable;
import java.util.List;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportResultDTO implements Serializable {

    private List<ExportDataSection> data;
    private Long nextOrder = -1L;
    private String sort;

    public ExportResultDTO(List<ExportDataSection> data, Long nextOrder, String sort) {
        this.data = data;
        this.nextOrder = nextOrder;
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getNextOrder() {
        return nextOrder;
    }

    public void setNextOrder(Long nextOrder) {
        this.nextOrder = nextOrder;
    }

    public List<ExportDataSection> getData() {
        return data;
    }

    public void setData(List<ExportDataSection> data) {
        this.data = data;
    }


}
