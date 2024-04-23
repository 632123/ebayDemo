package com.ebay.demo.interceptor;

import com.ebay.demo.domain.User;
import com.ebay.demo.response.Response;
import com.ebay.demo.response.ResponseEnums;
import com.ebay.demo.utils.EncodeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user= EncodeUtils.decode(request.getHeader("userInfo"));
        if("admin".equals(user.getRole())){
            return true;
        }else {
            response.setContentType("application/json; charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(Response.fail(ResponseEnums.AUTH_FAIL));
            response.getWriter().write(jsonStr);
            return false;
        }
    }
}



