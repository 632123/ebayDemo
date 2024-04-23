package com.ebay.demo.service;

import com.ebay.demo.domain.EndPoint;
import com.ebay.demo.exception.CustomException;


public interface BasicService {

    void addUser(EndPoint endPoint) throws CustomException;

    void userAddResource(String userId,String resource) throws CustomException;
}
