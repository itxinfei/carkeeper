package com.heima.shop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.common.exception.CustException;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.order.dtos.ServiceAndOrderDto;
import com.heima.model.shop.pojos.ServiceDetail;
import com.heima.model.shop.pojos.ServiceOrderLog;
import com.heima.model.shop.pojos.Store;
import com.heima.shop.mapper.ServiceDetailMapper;
import com.heima.shop.mapper.ServiceOrderLogMapper;
import com.heima.shop.service.ServiceInfo;
import com.heima.shop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceInfoImpl implements ServiceInfo {

    @Autowired
    StoreService storeService;

    @Autowired
    ServiceDetailMapper serviceDetailMapper;

    @Autowired
    ServiceOrderLogMapper serviceOrderLogMapper;

    /**
     * 查询服务集合
     *
     * @param serviceAndOrderDto
     * @return
     */
    @Override
    public Map findServiceAndStore(ServiceAndOrderDto serviceAndOrderDto) {
        if (serviceAndOrderDto==null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }

        Map<String, Object> map=new HashMap<>();
        //获得商家信息
        Long codeStore = serviceAndOrderDto.getStoreId();
        if (codeStore!=null){
            Store store = storeService.getOne(Wrappers.<Store>lambdaQuery().eq(Store::getCode,codeStore));
            if (store!=null){
                map.put("store",store);
            }
        }

        //获得服务操作记录
        Long orderId = serviceAndOrderDto.getOrderId();
        if (orderId!=null){
            List<ServiceOrderLog> serviceOrderLogs = serviceOrderLogMapper.selectList(Wrappers.<ServiceOrderLog>lambdaQuery()
                    .eq(ServiceOrderLog::getOrderId, orderId));
            if (serviceOrderLogs!=null&&serviceOrderLogs.size()>0){
                map.put("serviceOrderLogs",serviceOrderLogs);
            }
        }

        List<Long> serviceIds = serviceAndOrderDto.getServiceIds();
        List<ServiceDetail> serviceDetails = new ArrayList<>();
        //获得服务项目信息
        if (serviceIds!=null&&serviceIds.size()>0){
            for (Long serviceId : serviceIds) {
                ServiceDetail serviceDetail = serviceDetailMapper.selectOne(Wrappers.<ServiceDetail>lambdaQuery()
                        .eq(ServiceDetail::getServiceId, serviceId));
                serviceDetails.add(serviceDetail);
            }
            if (serviceDetails!=null&&serviceDetails.size()>0){
                map.put("serviceDetails", serviceDetails);
            }
        }

        return map;
    }
}
