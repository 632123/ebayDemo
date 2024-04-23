/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ebay.demo.web;

import com.ebay.demo.domain.EndPoint;
import com.ebay.demo.domain.User;
import com.ebay.demo.exception.CustomException;
import com.ebay.demo.response.Response;
import com.ebay.demo.response.ResponseEnums;
import com.ebay.demo.service.BasicService;
import com.ebay.demo.utils.EncodeUtils;
import com.sun.xml.internal.ws.api.message.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BasicController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    BasicService basicService;
    @PostMapping("/admin/addUser")
    public Response addUser(@RequestBody EndPoint endPoint) throws CustomException {
               basicService.addUser(endPoint);
               return Response.success();
    }

    @GetMapping("/user/{resource}")
    public Response userAddResource(@PathVariable("resource") String resource) throws CustomException {
        User user= EncodeUtils.decode(request.getHeader("userInfo"));
        basicService.userAddResource(user.getUserId(),resource);
            return Response.success();
    }
}
