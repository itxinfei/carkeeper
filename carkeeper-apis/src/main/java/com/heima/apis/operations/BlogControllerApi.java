package com.heima.apis.operations;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.operations.dtos.BlogDto;
import com.heima.model.operations.dtos.BlogInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * blog管理接口
 * @author heima
 *
 */
@Api(value = "blog管理", tags = "blog", description = "blog管理API")
public interface BlogControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(BlogInfoDto dto);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    public ResponseResult findBlogById(Long id);

    /**
     * 新增或修改
     * @param  dto
     * @return
     */
    @ApiOperation("新增或修改")
    public ResponseResult save(BlogDto dto);

    /**
     * 发布或取消
     * @param dto
     * @return
     */
    @ApiOperation("发布或取消")
    public ResponseResult isPublish(BlogDto dto);

}
