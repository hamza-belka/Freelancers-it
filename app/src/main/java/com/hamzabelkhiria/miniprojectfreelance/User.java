package com.hamzabelkhiria.miniprojectfreelance;

import java.util.Date;

public class User {
    int userid;
    String username;
    String fullName;
    Date sessionExpiryDate;
    String userimage;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public User() {
    }

    public User(String username, String fullName, Date sessionExpiryDate, String role) {
        this.username = username;
        this.fullName = fullName;
        this.sessionExpiryDate = sessionExpiryDate;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }
}
