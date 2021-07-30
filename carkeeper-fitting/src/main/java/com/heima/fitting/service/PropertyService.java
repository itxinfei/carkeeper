package com.heima.fitting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.fitting.dtos.PropertyDto;
import com.heima.model.fitting.pojos.Property;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface PropertyService extends IService<Property> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(PropertyDto dto);

    /**
     * 新增 和修改
     * @param property
     * @return
     */
    public ResponseResult saveAndUpdate(Property property);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

}
