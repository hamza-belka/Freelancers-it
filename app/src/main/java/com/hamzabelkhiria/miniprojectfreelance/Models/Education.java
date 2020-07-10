package com.hamzabelkhiria.miniprojectfreelance.Models;

public class Education {
    private int id;
    private String school;
    private String degree;
    private String startstudydate,endstudydate;
    private int userid;

    public Education() {
    }

    public Education(String school, String degree, String startstudydate, String endstudydate) {
        this.school = school;
        this.degree = degree;
        this.startstudydate = startstudydate;
        this.endstudydate = endstudydate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStartstudydate() {
        return startstudydate;
    }

    public void setStartstudydate(String startstudydate) {
        this.startstudydate = startstudydate;
    }

    public String getEndstudydate() {
        return endstudydate;
    }

    public void setEndstudydate(String endstudydate) {
        this.endstudydate = endstudydate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
