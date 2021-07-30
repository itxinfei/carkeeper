package com.heima.apis.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.shop.dtos.TypeParentDto;
import com.heima.model.shop.pojos.TypeParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * typeParent管理接口
 * @author 黑马
 *
 */
@Api(value = "typeParent管理", tags = "typeParent", description = "typeParent管理API")
public interface TypeParentControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(TypeParentDto dto);

    /**
     * 新增
     * @param  typeParent
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(TypeParent typeParent);

    /**
     * 修改
     * @param  typeParent
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(TypeParent typeParent);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);
}
