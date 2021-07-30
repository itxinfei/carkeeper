package com.heima.apis.order;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.order.dtos.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * order管理接口
 * @author hjl
 *
 */
@Api(value = "order管理", tags = "order", description = "order管理API")
public interface OrderControllerApi {

    /**
     * 关闭未处理过期服务订单
     * @param orderId
     * @return
     */
    @ApiOperation("关闭未处理过期服务订单")
    @ApiParam(value = "订单id",required = true)
    public ResponseResult closeServiceOrder(Long orderId);

    /**
     * 查询服务订单业务中的 未处理订单列表
     *
     * @param dto
     * @return
     */
    @ApiOperation("查询未处理订单列表")
    @ApiParam(value = "订单查询参数")
    public ResponseResult findNotProcessPage(@RequestBody OrderDto dto);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @ApiOperation("查询订单详情")
    @ApiParam(value = "订单类型和订单id",required = true)
    public ResponseResult findPartOrderInfo(Integer orderStatus,Long orderId);

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    @ApiParam(value = "订单查询参数")
    public ResponseResult findPartOrderPage(OrderDto dto);

}
