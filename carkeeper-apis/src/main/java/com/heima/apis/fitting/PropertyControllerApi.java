package com.heima.apis.fitting;

import com.heima.model.fitting.dtos.PropertyDto;
import com.heima.model.fitting.pojos.Property;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * property管理接口
 * @author author
 *
 */
@Api(value = "property管理", tags = "property", description = "property管理API")
public interface PropertyControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(PropertyDto dto);

    /**
     * 新增
     * @param  property
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Property property);

    /**
     * 修改
     * @param  property
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Property property);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);
}
