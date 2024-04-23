package com.ebay.demo.exception;

import com.ebay.demo.response.Response;
import com.ebay.demo.response.ResponseEnums;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Response handleException(Exception e){
        if(e instanceof CustomException) {
            return Response.fail(ResponseEnums.AUTH_FAIL);
        }else{
            return Response.fail(ResponseEnums.EXCEPTION);
        }
    }
}
