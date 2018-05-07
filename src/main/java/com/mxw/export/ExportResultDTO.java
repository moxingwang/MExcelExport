package com.mxw.export;

import java.io.Serializable;
import java.util.List;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportResultDTO implements Serializable {

    private List<ExportDataSection> data;
    private Long order = -1L;

    public ExportResultDTO(List<ExportDataSection> data, Long order) {
        this.data = data;
        this.order = order;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public List<ExportDataSection> getData() {
        return data;
    }

    public void setData(List<ExportDataSection> data) {
        this.data = data;
    }


}
