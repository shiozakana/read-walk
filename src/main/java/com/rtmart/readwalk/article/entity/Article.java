package com.rtmart.readwalk.article.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column
    private String link;

    @Column(columnDefinition = "text")
    private String content;

    @Column(length = 50)
    private String recommendName;

    @Column(length = 50)
    private String recommendNo;

    @Column
    private Date recommendTime;

    @Column(length = 50)
    private String area;

    @Column(length = 50)
    private String store;

    @Column
    private Date createTime;

    @Column
    private Date changeTime;

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
