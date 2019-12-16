package com.example.platform.pojo;

import java.util.Date;

public class WorkordersLog {
    private Integer id;

    private String node;

    private String organName;

    private String dealWay;

    private String dealContent;

    private Date dealTime;

    private String consumeTime;

    private String dealUser;

    private Integer workordersId;

    private String orderNumber;

    public WorkordersLog(Integer id, String node, String organName, String dealWay, String dealContent, Date dealTime, String consumeTime, String dealUser, Integer workordersId, String orderNumber) {
        this.id = id;
        this.node = node;
        this.organName = organName;
        this.dealWay = dealWay;
        this.dealContent = dealContent;
        this.dealTime = dealTime;
        this.consumeTime = consumeTime;
        this.dealUser = dealUser;
        this.workordersId = workordersId;
        this.orderNumber = orderNumber;
    }

    public WorkordersLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node == null ? null : node.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay == null ? null : dealWay.trim();
    }

    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent == null ? null : dealContent.trim();
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime == null ? null : consumeTime.trim();
    }

    public String getDealUser() {
        return dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser == null ? null : dealUser.trim();
    }

    public Integer getWorkordersId() {
        return workordersId;
    }

    public void setWorkordersId(Integer workordersId) {
        this.workordersId = workordersId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }
}