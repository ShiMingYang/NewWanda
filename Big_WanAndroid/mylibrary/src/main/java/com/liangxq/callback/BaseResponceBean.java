package com.liangxq.callback;


public class BaseResponceBean<T> {
    String errorMsg;
    int errorCode;
    T data;
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = data;
    }
}
