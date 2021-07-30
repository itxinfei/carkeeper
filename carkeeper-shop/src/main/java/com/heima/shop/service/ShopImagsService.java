package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.store.dtos.ShopImagsDto;
import com.heima.model.store.pojos.ShopImags;

/**
 * @Description:
 * @Version: V1.0
 */
public interface ShopImagsService extends IService<ShopImags> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(ShopImagsDto dto);

    /**
     * 新增
     * @param shopImags
     * @return
     */
    public ResponseResult insert(ShopImags shopImags);

    /**
     * 修改
     * @param shopImags
     * @return
     */
    public ResponseResult update(ShopImags shopImags);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

}
