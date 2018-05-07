package com.mxw.export;

import java.io.Serializable;
import java.util.List;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportResultDTO implements Serializable {

    private List<ExportDataSection> data;
    private Long nextId = -1L;

    public ExportResultDTO(List<ExportDataSection> data, Long nextId) {
        this.data = data;
        this.nextId = nextId;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    public List<ExportDataSection> getData() {
        return data;
    }

    public void setData(List<ExportDataSection> data) {
        this.data = data;
    }


}
