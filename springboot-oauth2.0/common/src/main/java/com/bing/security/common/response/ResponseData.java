package com.bing.security.common.response;


public class ResponseData<T> {
    int code;
    String message;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseData fail(RespEnum respenum) {
        return new ResponseData(respenum.getCode(), respenum.getMsg());
    }

    public static ResponseData fail(String message) {
        return new ResponseData(RespEnum.FAIL.getCode(), message);
    }

    public static<T> ResponseData success(T data) {
        return new ResponseData(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMsg(), data);
    }

    public static ResponseData success() {
        return new ResponseData(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMsg(), null);
    }

}
