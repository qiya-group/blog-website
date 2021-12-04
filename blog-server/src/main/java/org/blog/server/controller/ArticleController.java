package org.blog.server.controller;

import org.blog.server.common.ResponseUtils;
import org.blog.server.dto.PageDTO;
import org.blog.server.dto.ResponseDTO;
import org.blog.server.entity.Article;
import org.blog.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/selectByPrimaryKey")
    public ResponseDTO<Article> selectByPrimaryKey(@Validated Integer id) throws Exception {
        Article article = this.articleService.selectByPrimaryKey(id);
        ResponseDTO<Article> responseDTO = new ResponseDTO<>();
        if (article == null) {
            throw new Exception("未找到对应ID的文章！");
        }
        responseDTO.setData(article);
        responseDTO.setTime(new Date());
        return responseDTO;
    }

    @PostMapping("/selectAllArticles")
    public ResponseDTO<List<Article>> selectAllArticles(@Validated @RequestBody PageDTO pageDTO) {

    }
}
