package com.ebay.demo.utils;

import com.alibaba.fastjson.JSON;
import com.ebay.demo.domain.User;
import java.util.Base64;

public class EncodeUtils {
    public static String encode(User user){
        return Base64.getEncoder().encodeToString(JSON.toJSONString(user).getBytes());
    }
    public static User decode(String base64)  {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        String str = new String(decodedBytes);
        return JSON.parseObject(str, User.class);


    }
}
