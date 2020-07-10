package com.hamzabelkhiria.miniprojectfreelance.Models;

public class Message {
    private int id;
    private String sendername;
    private int senderid;
    private int receiverid;
    private String sendertoken;
    private String receivertoken;
    private String senderimage;
    private  String title;
    private String object;
    private String messagebody;
    private String date;

    public Message() {
    }

    public Message(int senderid, int receiverid, String sendertoken, String receivertoken, String title, String object, String messagebody, String date) {
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.sendertoken = sendertoken;
        this.receivertoken = receivertoken;
        this.title = title;
        this.object = object;
        this.messagebody = messagebody;
        this.date = date;
    }

    public String getSenderimage() {
        return senderimage;
    }

    public void setSenderimage(String senderimage) {
        this.senderimage = senderimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSendertoken() {
        return sendertoken;
    }

    public void setSendertoken(String sendertoken) {
        this.sendertoken = sendertoken;
    }

    public String getReceivertoken() {
        return receivertoken;
    }

    public void setReceivertoken(String receivertoken) {
        this.receivertoken = receivertoken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSendermail() {
        return sendername;
    }

    public void setSendermail(String sendermail) {
        this.sendername = sendermail;
    }

    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public String getMessagetxt() {
        return messagebody;
    }

    public void setMessagetxt(String messagetxt) {
        this.messagebody = messagetxt;
    }
}
