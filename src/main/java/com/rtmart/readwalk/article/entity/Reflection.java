package com.rtmart.readwalk.article.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long articleId;

    @Column(columnDefinition = "text")
    private String content;

    @Column
    private Date createTime;

    @Column
    private Date changeTime;

    @Column(length = 50)
    private String authorName;

    @Column(length = 50)
    private String authorNo;

    @Column(length = 50)
    private String area;

    @Column(length = 50)
    private String store;

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
}
