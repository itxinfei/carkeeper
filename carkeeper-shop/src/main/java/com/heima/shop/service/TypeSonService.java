package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.shop.dtos.TypeSonDto;
import com.heima.model.shop.pojos.TypeSon;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface TypeSonService extends IService<TypeSon> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(TypeSonDto dto);

    /**
     * 新增
     * @param typeSon
     * @return
     */
    public ResponseResult insert(TypeSon typeSon);

    /**
     * 修改
     * @param typeSon
     * @return
     */
    public ResponseResult update(TypeSon typeSon);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Long id);

}
