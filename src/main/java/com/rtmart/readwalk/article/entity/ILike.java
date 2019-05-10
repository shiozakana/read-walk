package com.rtmart.readwalk.article.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ILike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long contentId;

    @Column(length = 50)
    private String likeName;

    @Column(length = 50)
    private String likeNo;

    @Column(length = 50)
    private String area;

    @Column(length = 50)
    private String store;

    /**
     * 1:文章   2:读后感
     */
    @Column
    private String type;

    @Column
    private Date likeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getLikeName() {
        return likeName;
    }

    public void setLikeName(String likeName) {
        this.likeName = likeName;
    }

    public String getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(String likeNo) {
        this.likeNo = likeNo;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}
