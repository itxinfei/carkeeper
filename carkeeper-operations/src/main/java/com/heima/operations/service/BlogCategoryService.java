package com.heima.operations.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.operations.dtos.BlogCategoryDto;
import com.heima.model.operations.pojos.BlogCategory;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    /**
     * 查询所有分类
     * @return
     */
    public ResponseResult findAll();

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(BlogCategoryDto dto);

    /**
     * 新增或修改
     * @param blogCategory
     * @return
     */
    public ResponseResult saveBlogCategory(BlogCategory blogCategory);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Long id);

}
