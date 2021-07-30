package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.order.dtos.PartAndOrderDto;
import com.heima.model.shop.pojos.Part;

import java.util.Map;

/**
 * @Description:
 * @Version: V1.0
 */
public interface PartService extends IService<Part> {

    /**
     * 查询配件集合
     * @param partAndOrderDto
     * @return
     */

    public Map findPartsAndStore(PartAndOrderDto partAndOrderDto);

}
