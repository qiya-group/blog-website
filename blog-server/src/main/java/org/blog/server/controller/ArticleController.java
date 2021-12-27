package org.blog.server.controller;

import org.blog.server.common.Constant;
import org.blog.server.dto.PageDTO;
import org.blog.server.dto.ResponseDTO;
import org.blog.server.entity.Article;
import org.blog.server.common.error.ArticleException;
import org.blog.server.common.error.SQLInjectException;
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
    public ResponseDTO<Article> selectByPrimaryKey(@Validated Integer id) throws ArticleException {
        Article article = this.articleService.selectByPrimaryKey(id);
        ResponseDTO<Article> responseDTO = new ResponseDTO<>();
        if (article == null) {
            throw new ArticleException(Constant.CAN_NOT_FIND_ARTICLE_ID);
        }
        responseDTO.setData(article);
        responseDTO.setTime(new Date());
        responseDTO.setReCode(200);
        responseDTO.setMessage(Constant.REQUEST_SUCCESS);
        return responseDTO;
    }

    @GetMapping("/selectAllArticles")
    public ResponseDTO<List<Article>> selectAllArticles(@Validated @RequestBody PageDTO pageDTO) throws SQLInjectException {
        List<Article> articles = this.articleService.selectAllArticles(pageDTO);
        ResponseDTO<List<Article>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(articles);
        responseDTO.setTime(new Date());
        responseDTO.setReCode(200);
        responseDTO.setMessage(Constant.REQUEST_SUCCESS);
        return responseDTO;
    }

    @PostMapping("/insert")
    public ResponseDTO<Integer> insert(@Validated @RequestBody Article article) {
        int result = this.articleService.insert(article);
        ResponseDTO<Integer> responseDTO = new ResponseDTO<>();
        responseDTO.setData(result);
        responseDTO.setTime(new Date());
        if (result > 0) {
            responseDTO.setReCode(200);
            responseDTO.setMessage(Constant.REQUEST_SUCCESS);
        } else {
            responseDTO.setReCode(400);
            responseDTO.setMessage(Constant.REQUEST_ERROR);
        }
        return responseDTO;
    }
}
