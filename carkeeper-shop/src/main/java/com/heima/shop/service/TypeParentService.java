package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.shop.dtos.TypeParentDto;
import com.heima.model.shop.pojos.TypeParent;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface TypeParentService extends IService<TypeParent> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(TypeParentDto dto);

    /**
     * 新增
     * @param typeParent
     * @return
     */
    public ResponseResult insert(TypeParent typeParent);

    /**
     * 修改
     * @param typeParent
     * @return
     */
    public ResponseResult update(TypeParent typeParent);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

}
