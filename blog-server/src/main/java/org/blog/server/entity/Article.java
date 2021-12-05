package org.blog.server.entity;

import java.util.Date;

public class Article {
    /**
     * 文章ID
     */
    private Integer id;
    /**
     * 文章Key
     */
    private String key;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 发布时间
     */
    private Date publishTime;
    /**
     * 文章正文
     */
    private String content;
    /**
     * 文章状态
     */
    private Integer status;
    /**
     * 访问次数
     */
    private Integer viewTime;
    /**
     * 标签
     */
    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewTime() {
        return viewTime;
    }

    public void setViewTime(Integer viewTime) {
        this.viewTime = viewTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
