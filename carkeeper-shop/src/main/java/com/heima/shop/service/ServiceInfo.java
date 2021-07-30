package com.heima.shop.service;

import com.heima.model.order.dtos.ServiceAndOrderDto;

import java.util.Map;

public interface ServiceInfo {

    /**
     * 查询服务集合
     *
     * @param serviceAndOrderDto
     * @return
     */
    public Map findServiceAndStore(ServiceAndOrderDto serviceAndOrderDto);
}
