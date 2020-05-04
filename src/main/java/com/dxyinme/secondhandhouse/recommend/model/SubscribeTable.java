package com.dxyinme.secondhandhouse.recommend.model;

/**
    * 存储关注关系的表
    */
public class SubscribeTable {
    /**
    * id
    */
    private String subscribeId;

    /**
    * user_id
    */
    private Integer userId;

    /**
    * house_id
    */
    private String houseId;

    /**
    * 关注时间
    */
    private String timetable;

    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}