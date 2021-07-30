package com.heima.order.controller.v1;

import com.heima.apis.order.OrderControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.order.dtos.OrderDto;
import com.heima.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/order")
public class OrderController implements OrderControllerApi {

    @Autowired
    OrderService orderService;

    /**
     *
     *
     * 查询服务订单业务中的 未处理订单列表
     * @param dto
     * @return
     */
    @PostMapping("/findNotProcesslist")
    @Override
    public ResponseResult findNotProcessPage(@RequestBody OrderDto dto) {
        return orderService.findNotProcessPage(dto);
    }

    /**
     *
     *
     *    过
     *
     * 关闭未处理过期服务订单
     *
     * @param orderId
     * @return
     */
    @Override
    @PostMapping("/closeServiceOrder/{orderId}")
    public ResponseResult closeServiceOrder(@PathVariable("orderId") Long orderId) {
        return orderService.closeServiceOrder(orderId);
    }

    /**
     *
     *   过
     *
     * 查询列表
     * 1服务  2配件
     *
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPartOrderPage(@RequestBody OrderDto dto) {
        return orderService.findPartOrderPage(dto);
    }

    /**
     *
     *    过
     *
     *
     * 查询订单详情
     * 1服务  2配件
     *
     * @param orderId
     * @return
     */
    @GetMapping("/findOrder/{orderStatus}/{orderId}")
    @Override
    public ResponseResult findPartOrderInfo(@PathVariable("orderStatus") Integer orderStatus, @PathVariable("orderId") Long orderId) {
        if (orderStatus != null && orderStatus == 2) {
            return orderService.findPartOrderInfo(orderId);
        }
        if (orderStatus != null && orderStatus == 1){
          return   orderService.findServiceOrderInfo(orderId);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"服务正忙......");
    }
}
