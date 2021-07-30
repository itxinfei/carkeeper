package com.heima.shop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.order.dtos.PartAndOrderDto;
import com.heima.model.shop.pojos.Part;
import com.heima.model.shop.pojos.PartsOrderLog;
import com.heima.model.shop.pojos.Store;
import com.heima.shop.mapper.PartMapper;
import com.heima.shop.mapper.PartOrderLogMapper;
import com.heima.shop.service.PartService;
import com.heima.shop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class PartServiceImpl extends ServiceImpl<PartMapper, Part> implements PartService {

    @Autowired
    StoreService storeService;

    @Autowired
    PartMapper partMapper;

    @Autowired
    PartOrderLogMapper partOrderLogMapper;

    /**
     * 查询配件集合
     *
     * @param  partAndOrderDto
     * @return
     */
    @Override
    public Map findPartsAndStore(PartAndOrderDto partAndOrderDto) {
        if (partAndOrderDto==null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        Map<String, Object> map=new HashMap<>();

        //获得配件id
        List<Long> partIds = partAndOrderDto.getPartIds();
        //获得配件集合数据
        List<Part> parts = new ArrayList<>();
        if (partIds!=null&&partIds.size()>0){
            for (Long partId : partIds) {
                Part part = partMapper.selectById(partId);
                if (part!=null){
                    parts.add(part);
                }
            }
            if (parts!=null&&parts.size()>0){
                map.put("parts",parts);
            }
        }

        //获得商家id
        Long codeStore = partAndOrderDto.getStoreId();
        if (codeStore!=null){
            //获得商家信息
            Store store = storeService.getOne(Wrappers.<Store>lambdaQuery().eq(Store::getCode,codeStore));
            if (store!=null){
                map.put("store",store);
            }
        }

        //获得配件订单id
        Long orderId = partAndOrderDto.getOrderId();
        if (orderId!=null){
            List<PartsOrderLog> partsOrderLogs = partOrderLogMapper.selectList(Wrappers.<PartsOrderLog>lambdaQuery()
                    .eq(PartsOrderLog::getOrderId, orderId));
            if (partsOrderLogs!=null&&partsOrderLogs.size()>0){
                map.put("partsOrderLogs",partsOrderLogs);
            }
        }

        return map;
    }
}
