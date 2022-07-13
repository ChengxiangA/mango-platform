package com.chengxiang.mango.http;

import java.util.List;

public class HttpResult<T> {
    private int code = 200;

    private String msg;

    private T data;

    public static HttpResult error() {
        return new HttpResult(HttpStatus.ERROR,"发生未知错误");
    }

    public static HttpResult error(String msg) {
        return new HttpResult(HttpStatus.ERROR,msg);
    }

    public static HttpResult ok() {
        return new HttpResult(HttpStatus.SUCCESS,"成功了");
    }

    public static HttpResult ok(String msg) {
        return new HttpResult(HttpStatus.SUCCESS,msg);
    }



    public static <T> HttpResult<T> ok(String msg,T data) {
        return new HttpResult<>(msg,data);
    }

    public HttpResult() {
    }

    public HttpResult(String msg) {
        this.msg = msg;
    }

    public HttpResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HttpResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
