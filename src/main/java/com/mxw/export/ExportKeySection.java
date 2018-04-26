package com.mxw.export;

import java.io.Serializable;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportKeySection implements Serializable {
    private Long keyBegin;
    private Long keyEnd;
    private long order;

    public ExportKeySection() {
    }

    public ExportKeySection(Long keyBegin, Long keyEnd, long order) {
        this.keyBegin = keyBegin;
        this.keyEnd = keyEnd;
        this.order = order;
    }

    public Long getKeyBegin() {
        return keyBegin;
    }

    public void setKeyBegin(Long keyBegin) {
        this.keyBegin = keyBegin;
    }

    public Long getKeyEnd() {
        return keyEnd;
    }

    public void setKeyEnd(Long keyEnd) {
        this.keyEnd = keyEnd;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
