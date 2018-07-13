package com.lung.getdata.pojo;

public class PushUser {

    private Integer id;
    private Integer pid;
    private String openid;
    private String aid;
    private String nickname;
    private Long push_at;
    private Integer status;
    private Integer retried;
    private Long created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getPush_at() {
        return push_at;
    }

    public void setPush_at(Long push_at) {
        this.push_at = push_at;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRetried() {
        return retried;
    }

    public void setRetried(Integer retried) {
        this.retried = retried;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }
}
