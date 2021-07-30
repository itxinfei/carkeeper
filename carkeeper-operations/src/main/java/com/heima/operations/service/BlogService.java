package com.heima.operations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.operations.dtos.BlogDto;
import com.heima.model.operations.pojos.Blog;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @Version: V1.0
 */
public interface BlogService extends IService<Blog> {

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    public ResponseResult findBlogById(Long id);

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    public ResponseResult saveBlog(BlogDto dto);

    /**
     * 发布或取消
     * @param dto
     * @return
     */
    public ResponseResult isPublish(BlogDto dto);

    /**
     * 添加配件查询列表
     *
     * @return
     */
    public ResponseResult findComponentPage(@RequestBody DirectoryDto dto);

}
