package com.heima.apis.shop;

import com.heima.model.order.dtos.ServiceAndOrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * part管理接口
 * @author hjl
 *
 */
@Api(value = "service管理", tags = "service", description = "service管理API")
public interface ServiceControllerApi {

    /**
     * 查询服务集合
     * @param serviceAndOrderDto
     * @return
     */
    @ApiOperation("查询服务集合")
    public Map findServiceAndStore(@RequestBody ServiceAndOrderDto serviceAndOrderDto);

}
