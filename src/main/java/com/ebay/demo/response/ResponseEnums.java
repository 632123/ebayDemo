package com.ebay.demo.response;

public enum ResponseEnums {
    SUCCESS(200,"请求成功"),
    EXCEPTION(500,"请求失败，服务器异常"),
    AUTH_FAIL(501,"认证失败，账号无权限");
    public final Integer code;
    public final String msg;

    ResponseEnums(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
