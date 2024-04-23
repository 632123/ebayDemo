package com.ebay.demo.domain;


import java.util.List;

public class EndPoint {
    private String userId;
    private List<String> endpoint;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List<String> endpoint) {
        this.endpoint = endpoint;
    }

    public EndPoint(String userId, List<String> endpoint) {
        this.userId = userId;
        this.endpoint = endpoint;
    }

    public EndPoint() {
    }
}
