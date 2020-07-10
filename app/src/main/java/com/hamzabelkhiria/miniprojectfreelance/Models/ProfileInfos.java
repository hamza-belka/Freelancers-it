package com.hamzabelkhiria.miniprojectfreelance.Models;

public class ProfileInfos {
    private int id;
    private String profiletitle;
    private String about;
    private String location;
    private int userid;

    public ProfileInfos() {
    }

    public ProfileInfos(String profiletitle, String about, String location) {
        this.profiletitle = profiletitle;
        this.about = about;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfiletitle() {
        return profiletitle;
    }

    public void setProfiletitle(String profiletitle) {
        this.profiletitle = profiletitle;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
