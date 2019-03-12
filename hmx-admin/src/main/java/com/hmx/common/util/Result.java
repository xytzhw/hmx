package com.hmx.common.util;

import java.io.Serializable;

/**
 * @author shi
 * @create 2018-06-06  16:00
 * @description 标准返回值
 **/
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 2797695294117691139L;
    private int status;//返回状态
    private String msg;//返回提示信息
    private T data;//返回数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
