package com.chengxiang.mango.entity;

import java.time.LocalDate;

public class SysLoginLog extends BaseModel {

    public static final String STATUS_LOGIN = "login";
    public static final String STATUS_LOGOUT = "logout";
    public static final String STATUS_ONLINE = "online";


    private String userName;

    private String status;

    private String ip;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

}