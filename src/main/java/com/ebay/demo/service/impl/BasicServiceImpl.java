package com.ebay.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.ebay.demo.domain.EndPoint;
import com.ebay.demo.exception.CustomException;
import com.ebay.demo.response.ResponseEnums;
import com.ebay.demo.service.BasicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
public class BasicServiceImpl implements BasicService {
    private static final String filePath="tmp\\endpoint.txt";
    private static final String temFilePath="tmp\\tmpEndpoint.txt";
    public void BasicService(){
        File file=new File(filePath);
        if(!file.exists()){
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addUser(EndPoint endPoint) throws CustomException {
        int index=0;
        int count=0;
        String userId=endPoint.getUserId();
        File inputFile = new File(filePath);
        File outputFile = new File(temFilePath);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
                EndPoint linePoint = JSON.parseObject(line, EndPoint.class);
                if(userId.equals(linePoint.getUserId())){
                    line = objectMapper.writeValueAsString(endPoint);
                    index=count;
                }
                writer.write(line);
                writer.newLine();
            }
            if(index==0){
                writer.write(objectMapper.writeValueAsString(endPoint));
            }
            reader.close();
            writer.close();
            //文件读写完后删除旧文件，改名新文件
            inputFile.delete();
            outputFile.renameTo(new File(filePath));
        } catch (IOException e) {
            throw new CustomException(ResponseEnums.EXCEPTION.getCode(), e.getMessage());
        }

    }

    @Override
    public void userAddResource(String userId, String resource) throws CustomException {
        int index=0;
        int count=0;
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            File inputFile = new File(filePath);
            File outputFile = new File(temFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                count++;
                EndPoint linePoint = JSON.parseObject(line, EndPoint.class);
                //先判断是否是该用户
                if(userId.equals(linePoint.getUserId())){
                    //再判断是否包含该资源，不包含则追加
                    if(!linePoint.getEndpoint().contains(resource)){
                        linePoint.getEndpoint().add(resource);
                    }
                    line = objectMapper.writeValueAsString(linePoint);
                    index=count;
                }
                writer.write(line);
                writer.newLine();
            }
            if(index==0){
                EndPoint endPoint=new EndPoint();
                endPoint.setUserId(userId);
                endPoint.setEndpoint(Collections.singletonList(resource));
                writer.write(objectMapper.writeValueAsString(endPoint));
            }
            reader.close();
            writer.close();
            //文件读写完后删除旧文件，改名新文件
            inputFile.delete();
            outputFile.renameTo(new File(filePath));
        } catch (IOException e) {
            throw new CustomException(ResponseEnums.EXCEPTION.getCode(), e.getMessage());
        }
    }
}
