package org.blog.server.controller;

import org.blog.server.common.Constant;
import org.blog.server.common.ControllerMapping;
import org.blog.server.common.MappingLoader;
import org.blog.server.common.ResponseUtils;
import org.blog.server.common.annotion.ApiAuth;
import org.blog.server.dto.PageDTO;
import org.blog.server.dto.ResponseDTO;
import org.blog.server.entity.Article;
import org.blog.server.error.ArticleException;
import org.blog.server.error.SQLInjectException;
import org.blog.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MappingLoader mappingLoader;

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

    @PostMapping("/selectAllArticles")
    public ResponseDTO<List<Article>> selectAllArticles(@Validated @RequestBody PageDTO pageDTO) throws SQLInjectException {
        List<Article> articles = this.articleService.selectAllArticles(pageDTO);
        ResponseDTO<List<Article>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(articles);
        responseDTO.setTime(new Date());
        responseDTO.setReCode(200);
        responseDTO.setMessage(Constant.REQUEST_SUCCESS);
        return responseDTO;
    }

    @ResponseBody
    @ApiAuth(role = {"admin", "user"})
    @RequestMapping("/getAllUrl")
    public Map<String, ControllerMapping>  getAllUrl() {
        return this.mappingLoader.getAllUrl();
    }
}
