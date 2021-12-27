package org.blog.server.serviceImpl;

import org.blog.server.common.SqlUtils;
import org.blog.server.dto.PageDTO;
import org.blog.server.entity.Article;
import org.blog.server.common.error.SQLInjectException;
import org.blog.server.mapper.ArticleMapper;
import org.blog.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return this.articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Article record) {
        return this.articleMapper.insert(record);
    }

    @Override
    public Article selectByPrimaryKey(Integer id) {
        return this.articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return this.articleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Article> selectAllArticles(PageDTO page) throws SQLInjectException {
        String sql = SqlUtils.getSql(page);
        return this.articleMapper.selectAllArticles(sql);
    }
}
