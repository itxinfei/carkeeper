package com.heima.apis.operations;

import com.heima.model.operations.dtos.ArticleDto;
import com.heima.model.operations.pojos.Article;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * article管理接口
 * @author heima
 *
 */
@Api(value = "article管理", tags = "article", description = "article管理API")
public interface ArticleControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(ArticleDto dto);

    /**
     * 新增或修改
     * @param  dto
     * @return
     */
    @ApiOperation("新增或修改")
    public ResponseResult save(ArticleDto dto);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Long id);
}
