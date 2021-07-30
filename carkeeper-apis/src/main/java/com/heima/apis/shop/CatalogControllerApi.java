package com.heima.apis.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.es.pojo.CatalogVo;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.model.shop.pojos.Catalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * catalog管理接口
 * @author 黑马
 *
 */
@Api(value = "catalog管理", tags = "catalog", description = "catalog管理API")
public interface CatalogControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(CatalogDto dto);


    /**
     * 修改
     * @param  catalogVo
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(CatalogVo catalogVo);


}
