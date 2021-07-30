package com.heima.apis.shop;

import com.heima.model.order.dtos.PartAndOrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

/**
 * part管理接口
 * @author hjl
 *
 */
@Api(value = "part管理", tags = "part", description = "part管理API")
public interface PartControllerApi {

    /**
     * 查询配件集合
     * @param partAndOrderDto
     * @return
     */
    @ApiOperation("查询配件集合")
    public Map findPartsAndStore(PartAndOrderDto partAndOrderDto);

}
