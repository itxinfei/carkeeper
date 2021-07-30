package com.heima.apis.fitting;

import com.heima.model.fitting.dtos.CategoryDto;
import com.heima.model.fitting.pojos.Category;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * category管理接口
 * @author author
 *
 */
@Api(value = "category管理", tags = "category", description = "category管理API")
public interface CategoryControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(CategoryDto dto);

    /**
     * 新增
     * @param  category
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Category category);

    /**
     * 修改
     * @param  category
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);


    /**
     * 查询所有配件类型
     * @return
     */
    public ResponseResult findAllType();
}
