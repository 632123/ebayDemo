package com.ebay.demo.exception;

import java.io.Serializable;

public class CustomException extends Exception implements Serializable {
    private Integer code;
    private String msg;
    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public CustomException() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
