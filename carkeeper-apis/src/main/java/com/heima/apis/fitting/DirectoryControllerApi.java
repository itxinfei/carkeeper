package com.heima.apis.fitting;

import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.fitting.pojos.Directory;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * directory管理接口
 * @author author
 *
 */
@Api(value = "directory管理", tags = "directory", description = "directory管理API")
public interface DirectoryControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(DirectoryDto dto);

    /**
     * 新增
     * @param  directory
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Directory directory);

    /**
     * 修改
     * @param  directory
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Directory directory);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);
}
