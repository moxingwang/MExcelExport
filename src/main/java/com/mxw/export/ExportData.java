package com.mxw.export;

import java.io.Serializable;

/**
 * @author MoXingwang on 2018-04-25.
 */
public class ExportData implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
