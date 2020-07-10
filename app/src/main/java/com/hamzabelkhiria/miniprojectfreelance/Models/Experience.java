package com.hamzabelkhiria.miniprojectfreelance.Models;

public class Experience {
    private int id;
    private String exptitle;
    private String expcompany;
    private String startdate,enddate;
    private int userid;

    public Experience() {
    }

    public Experience(String exptitle, String expcompany, String startdate, String enddate) {
        this.exptitle = exptitle;
        this.expcompany = expcompany;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExptitle() {
        return exptitle;
    }

    public void setExptitle(String exptitle) {
        this.exptitle = exptitle;
    }

    public String getExpcompany() {
        return expcompany;
    }

    public void setExpcompany(String expcompany) {
        this.expcompany = expcompany;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
