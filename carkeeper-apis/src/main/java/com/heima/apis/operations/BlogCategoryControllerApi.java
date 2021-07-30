package com.heima.apis.operations;

import com.heima.model.operations.dtos.BlogCategoryDto;
import com.heima.model.operations.pojos.BlogCategory;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * blogCategory管理接口
 * @author heima
 *
 */
@Api(value = "blogCategory管理", tags = "blogCategory", description = "blogCategory管理API")
public interface BlogCategoryControllerApi {

    /**
     * 查询所有分类
     * @return
     */
    public ResponseResult findAll();

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(BlogCategoryDto dto);

    /**
     * 新增或修改
     * @param  blogCategory
     * @return
     */
    @ApiOperation("新增或修改")
    public ResponseResult save(BlogCategory blogCategory);


    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Long id);
}
