package com.heima.operations.controller.v1;

import com.heima.apis.operations.BlogControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.operations.dtos.BlogDto;
import com.heima.model.operations.dtos.BlogInfoDto;
import com.heima.operations.service.BlogInfoService;
import com.heima.operations.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController implements BlogControllerApi {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogInfoService blogInfoService;

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult findPage(@RequestBody BlogInfoDto dto) {
        return blogInfoService.findPage(dto);
    }

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @GetMapping("/one/{id}")
    @Override
    public ResponseResult findBlogById(@PathVariable("id") Long id) {
        return blogService.findBlogById(id);
    }

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody BlogDto dto) {
        return blogService.saveBlog(dto);
    }

    /**
     * 发布或取消
     * @param dto
     * @return
     */
    @Override
    @PostMapping("/publish")
    public ResponseResult isPublish(@RequestBody BlogDto dto) {
        return blogService.isPublish(dto);
    }

}
