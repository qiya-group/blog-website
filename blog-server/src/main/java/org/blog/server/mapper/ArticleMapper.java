package org.blog.server.mapper;

import org.blog.server.dto.PageDTO;
import org.blog.server.entity.Article;

import java.util.List;

public interface ArticleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Article record);

    List<Article> selectAllArticles(String sql);
}
