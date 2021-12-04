package org.blog.server.service;

import org.blog.server.entity.Article;

/**
 * 文章服务接口层
 */
public interface ArticleService {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Article record);
}
