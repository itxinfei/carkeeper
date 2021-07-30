package com.heima.operations.controller.v1;

import  com.heima.operations.service.ArticleService;
import  com.heima.apis.operations.ArticleControllerApi;
import  com.heima.model.operations.dtos.ArticleDto;
import  com.heima.model.operations.pojos.Article;
import  com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/article")
public class ArticleController implements ArticleControllerApi {

    @Autowired
    ArticleService articleService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody ArticleDto dto) {
        return articleService.findPage(dto);
    }

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody ArticleDto dto) {
        return articleService.saveArticle(dto);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        return articleService.deleteById(id);
    }
}
