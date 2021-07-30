package com.heima.apis.user;

import com.heima.model.user.pojos.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * car管理接口
 * @author hjl
 *
 */
@Api(value = "car管理", tags = "car", description = "car管理API")
public interface CarControllerApi {

    /**
     * 查询车主
     * @param carId
     * @return
     */
    @ApiOperation("查询车主")
    public Car findCar( Long carId);

}
