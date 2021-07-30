package com.heima.operations.controller.v1;

import  com.heima.operations.service.BlogCategoryService;
import  com.heima.apis.operations.BlogCategoryControllerApi;
import  com.heima.model.operations.dtos.BlogCategoryDto;
import  com.heima.model.operations.pojos.BlogCategory;
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
@RequestMapping("/api/v1/blogCategory")
public class BlogCategoryController implements BlogCategoryControllerApi {

    @Autowired
    BlogCategoryService blogCategoryService;

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    @GetMapping("/all")
    public ResponseResult findAll() {
        return blogCategoryService.findAll();
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody BlogCategoryDto dto) {
        return blogCategoryService.findPage(dto);
    }

    /**
     * 新增或修改
     * @param blogCategory
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody BlogCategory blogCategory) {
        return blogCategoryService.saveBlogCategory(blogCategory);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        return blogCategoryService.deleteById(id);
    }
}
