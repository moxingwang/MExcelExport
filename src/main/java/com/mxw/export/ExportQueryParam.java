package com.mxw.export;

import java.io.Serializable;

/**
 * @author MoXingwang on 2018/4/23.
 */
public class ExportQueryParam<T> implements Serializable {
    private long keyBegin;
    private long keyEnd;
    private int order;

    private T parameter;

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }

    public long getKeyBegin() {
        return keyBegin;
    }

    public void setKeyBegin(long keyBegin) {
        this.keyBegin = keyBegin;
    }

    public long getKeyEnd() {
        return keyEnd;
    }

    public void setKeyEnd(long keyEnd) {
        this.keyEnd = keyEnd;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
