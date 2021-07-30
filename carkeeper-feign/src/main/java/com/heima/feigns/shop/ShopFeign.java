package com.heima.feigns.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.order.dtos.PartAndOrderDto;
import com.heima.model.order.dtos.ServiceAndOrderDto;
import com.heima.model.shop.dtos.ServiceAndOrderLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "carkeeper-shop")
public interface ShopFeign {

    /**
     * 查询配件集合
     * @param partAndOrderDto
     * @return
     */
    @PostMapping("/api/v1/part/findPartsAndStore")
    public Map findPartsAndStore(@RequestBody PartAndOrderDto partAndOrderDto);

    /**
     * 查询服务集合
     * @param serviceAndOrderDto
     * @return
     */
    @PostMapping("/api/v1/service/findServiceAndStore")
    public Map findServiceAndStore(@RequestBody ServiceAndOrderDto serviceAndOrderDto);

    /**
     * 插入操作员操作记录
     */
    @PostMapping("/api/v1/serviceLog/insertServiceLong")
    ResponseResult insertServiceLong(@RequestBody ServiceAndOrderLogDto dto);

}
