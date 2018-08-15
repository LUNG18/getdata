package com.lung.getdata.pojo;

public class Comment {
    private Integer id;
    private String artid;
    private String openid;
    private Integer parentid;
    private Integer commentNumers;
    private Integer praiseNumers;
    private Integer isdisplay;
    private Integer isread;
    private Long createdAt;
    private Long lastModified;
    private Integer opStatus;
    private Integer isFromAdmin;
    private Integer corder;
    private Integer findCorder;
    private Integer hotCorder;
    private Integer sptFor;
    private String labels;
    private String title;
    private String content;
    private String aid;
    private String images;
    private Integer type;
    private Integer publishAt;
    private String tags;
    private Integer isFollowed;
    private Integer groupid;
    private Integer subjectId;
    private Integer bookTime;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", artid=" + artid +
                ", openid=" + openid +
                ", parentid=" + parentid +
                ", commentNumers=" + commentNumers +
                ", praiseNumers=" + praiseNumers +
                ", isdisplay=" + isdisplay +
                ", isread=" + isread +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                ", opStatus=" + opStatus +
                ", isFromAdmin=" + isFromAdmin +
                ", corder=" + corder +
                ", findCorder=" + findCorder +
                ", hotCorder=" + hotCorder +
                ", sptFor=" + sptFor +
                ", labels=" + labels +
                ", title=" + title +
                ", content=" + content +
                ", aid=" + aid +
                ", images=" + images +
                ", type=" + type +
                ", publishAt=" + publishAt +
                ", tags=" + tags +
                ", isFollowed=" + isFollowed +
                ", groupid=" + groupid +
                ", subjectId=" + subjectId +
                ", bookTime=" + bookTime +
                "}";
    }

    public Integer getPraiseNumers() {
        return praiseNumers;
    }

    public void setPraiseNumers(Integer praiseNumers) {
        this.praiseNumers = praiseNumers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtid() {
        return artid;
    }

    public void setArtid(String artid) {
        this.artid = artid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getCommentNumers() {
        return commentNumers;
    }

    public void setCommentNumers(Integer commentNumers) {
        this.commentNumers = commentNumers;
    }

    public Integer getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Integer isdisplay) {
        this.isdisplay = isdisplay;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(Integer opStatus) {
        this.opStatus = opStatus;
    }

    public Integer getIsFromAdmin() {
        return isFromAdmin;
    }

    public void setIsFromAdmin(Integer isFromAdmin) {
        this.isFromAdmin = isFromAdmin;
    }

    public Integer getCorder() {
        return corder;
    }

    public void setCorder(Integer corder) {
        this.corder = corder;
    }

    public Integer getFindCorder() {
        return findCorder;
    }

    public void setFindCorder(Integer findCorder) {
        this.findCorder = findCorder;
    }

    public Integer getHotCorder() {
        return hotCorder;
    }

    public void setHotCorder(Integer hotCorder) {
        this.hotCorder = hotCorder;
    }

    public Integer getSptFor() {
        return sptFor;
    }

    public void setSptFor(Integer sptFor) {
        this.sptFor = sptFor;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Integer publishAt) {
        this.publishAt = publishAt;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Integer isFollowed) {
        this.isFollowed = isFollowed;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getBookTime() {
        return bookTime;
    }

    public void setBookTime(Integer bookTime) {
        this.bookTime = bookTime;
    }
}
