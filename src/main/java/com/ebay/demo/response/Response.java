package com.ebay.demo.response;


public class Response {
    private Integer code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Response() {
    }
    public static Response success(){
        return new Response(ResponseEnums.SUCCESS.getCode(),ResponseEnums.SUCCESS.getMsg());
    }

    public static Response fail(ResponseEnums responseEnums){
        return new Response(responseEnums.getCode(),responseEnums.getMsg());
    }
}
