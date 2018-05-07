package com.mxw.export;

import java.io.Serializable;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportQueryParam<T> implements Serializable {
    private long order;
    private T parameter;

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }
}
