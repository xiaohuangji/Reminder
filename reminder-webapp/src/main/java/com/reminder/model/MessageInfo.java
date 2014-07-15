package com.reminder.model;

import java.util.Date;

/**
 * Created by wills on 7/15/14.
 */
public class MessageInfo {

    private long id;

    private int fromId;

    private int toId;

    private int type;

    private String payload;

    public MessageInfo() {
    }

    public MessageInfo(int fromId, int toId, int type, String payload) {
        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.payload = payload;
    }

    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
