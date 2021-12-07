package org.blog.server.service;

import org.blog.server.dto.PageDTO;
import org.blog.server.entity.Article;
import org.blog.server.error.SQLInjectException;

import java.util.List;

/**
 * 文章服务接口层
 */
public interface ArticleService {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Article record);

    List<Article> selectAllArticles(PageDTO pageDTO) throws SQLInjectException;
}
