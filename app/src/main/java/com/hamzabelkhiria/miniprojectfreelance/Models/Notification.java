package com.hamzabelkhiria.miniprojectfreelance.Models;

import java.util.Date;

public class Notification  {
    private int notificationid;
    private int notifierid;
    private String notificationtitle;
    private String notifiermail;
    private String notificationobject;
    private String notificationtxt;
    private Date notificationdate;
    private int receiverid;

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public Notification() {
    }

    public Notification(int notifierid, String notifiermail, String notificationobject, String notificationtxt, Date notificationdate) {
        this.notifierid = notifierid;
        this.notifiermail = notifiermail;
        this.notificationobject = notificationobject;
        this.notificationtxt = notificationtxt;
        this.notificationdate = notificationdate;
    }

    public Notification(int notifierid, String notificationtitle, String notifiermail, String notificationobject, String notificationtxt, Date notificationdate) {
        this.notifierid = notifierid;
        this.notificationtitle = notificationtitle;
        this.notifiermail = notifiermail;
        this.notificationobject = notificationobject;
        this.notificationtxt = notificationtxt;
        this.notificationdate = notificationdate;
    }

    public Notification(int notifierid, String notificationtitle, String notifiermail, String notificationobject, String notificationtxt, Date notificationdate, int receiverid) {
        this.notifierid = notifierid;
        this.notificationtitle = notificationtitle;
        this.notifiermail = notifiermail;
        this.notificationobject = notificationobject;
        this.notificationtxt = notificationtxt;
        this.notificationdate = notificationdate;
        this.receiverid = receiverid;
    }

    public String getNotificationtitle() {
        return notificationtitle;
    }

    public void setNotificationtitle(String notificationtitle) {
        this.notificationtitle = notificationtitle;
    }

    public int getNotifierid() {
        return notifierid;
    }

    public void setNotifierid(int notifierid) {
        this.notifierid = notifierid;
    }

    public String getNotifiermail() {
        return notifiermail;
    }

    public void setNotifiermail(String notifiermail) {
        this.notifiermail = notifiermail;
    }

    public String getNotificationobject() {
        return notificationobject;
    }

    public void setNotificationobject(String notificationobject) {
        this.notificationobject = notificationobject;
    }

    public String getNotificationtxt() {
        return notificationtxt;
    }

    public void setNotificationtxt(String notificationtxt) {
        this.notificationtxt = notificationtxt;
    }

    public Date getNotificationdate() {
        return notificationdate;
    }

    public void setNotificationdate(Date notificationdate) {
        this.notificationdate = notificationdate;
    }
}
