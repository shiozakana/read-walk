package com.rtmart.readwalk.article.dto;


import java.util.Date;

public class ReflectionDto {

    private Long id;

    private Long articleId;

    private String content;

    private Date createTime;

    private Date changeTime;

    private String authorName;

    private String authorNo;

    private String area;

    private String store;

    private boolean likeFlag;

    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLikeFlag() {
        return likeFlag;
    }

    public void setLikeFlag(boolean likeFlag) {
        this.likeFlag = likeFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorNo() {
        return authorNo;
    }

    public void setAuthorNo(String authorNo) {
        this.authorNo = authorNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ReflectionDto{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", changeTime=" + changeTime +
                ", authorName='" + authorName + '\'' +
                ", authorNo='" + authorNo + '\'' +
                ", area='" + area + '\'' +
                ", store='" + store + '\'' +
                ", likeFlag=" + likeFlag +
                ", likeCount=" + likeCount +
                '}';
    }
}
