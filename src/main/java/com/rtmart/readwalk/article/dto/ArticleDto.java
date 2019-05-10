package com.rtmart.readwalk.article.dto;


import java.util.Date;
import java.util.List;

public class ArticleDto {

    private Long id;

    private String name;

    private String link;

    private String content;

    private String recommendName;

    private String recommendNo;

    private Date recommendTime;

    private String area;

    private String store;

    private Date createTime;

    private Date changeTime;

    private boolean likeFlag;

    private int likeCount;

    private List<ReflectionDto> reflectionDtos;

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

    public List<ReflectionDto> getReflectionDtos() {
        return reflectionDtos;
    }

    public void setReflectionDtos(List<ReflectionDto> reflectionDtos) {
        this.reflectionDtos = reflectionDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", content='" + content + '\'' +
                ", recommendName='" + recommendName + '\'' +
                ", recommendNo='" + recommendNo + '\'' +
                ", recommendTime=" + recommendTime +
                ", area='" + area + '\'' +
                ", store='" + store + '\'' +
                ", createTime=" + createTime +
                ", changeTime=" + changeTime +
                '}';
    }
}
