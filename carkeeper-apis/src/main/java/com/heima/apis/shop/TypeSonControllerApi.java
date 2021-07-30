package com.heima.apis.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.shop.dtos.TypeSonDto;
import com.heima.model.shop.pojos.TypeSon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * typeSon管理接口
 * @author 黑马
 *
 */
@Api(value = "typeSon管理", tags = "typeSon", description = "typeSon管理API")
public interface TypeSonControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(TypeSonDto dto);

    /**
     * 新增
     * @param  typeSon
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(TypeSon typeSon);

    /**
     * 修改
     * @param  typeSon
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(TypeSon typeSon);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Long id);
}
