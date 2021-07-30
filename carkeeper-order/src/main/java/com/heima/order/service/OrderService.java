package com.heima.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.order.dtos.OrderDto;
import com.heima.model.order.pojos.Order;
import io.swagger.annotations.ApiOperation;

/**
 * @Description:
 * @Version: V1.0
 */
public interface OrderService extends IService<Order> {

    /**
     * 关闭未处理过期服务订单
     * @param orderId
     * @return
     */
    @ApiOperation("关闭未处理过期服务订单")
    public ResponseResult closeServiceOrder(Long orderId);

    /**
     * 查询服务订单详情
     * @param orderId
     * @return
     */
    @ApiOperation("查询服务订单详情")
    public ResponseResult findServiceOrderInfo(Long orderId);

    /**
     * 查询配件订单详情
     * @param orderId
     * @return
     */
    @ApiOperation("查询配件订单详情")
    public ResponseResult findPartOrderInfo(Long orderId);

    /**
     * 查询订单列表
     *  1服务  2配件
     * @param dto
     * @return
     */
    public ResponseResult findPartOrderPage(OrderDto dto);

    /**
     * 查询服务订单业务中的 未处理订单列表
     *
     * @param dto
     * @return
     */
    ResponseResult findNotProcessPage(OrderDto dto);


}
