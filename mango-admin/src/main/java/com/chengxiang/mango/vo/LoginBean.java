package com.chengxiang.mango.vo;

/**
 * @author 程祥
 * @date 2022/7/18 17:02
 */
public class LoginBean {
    private String username;

    private String password;

    private String captcha;

    public LoginBean(String username, String password, String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
