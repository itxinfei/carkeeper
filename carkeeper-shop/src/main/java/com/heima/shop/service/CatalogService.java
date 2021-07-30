package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.es.pojo.CatalogVo;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.model.shop.pojos.Catalog;
import com.heima.model.common.dtos.ResponseResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
public interface CatalogService extends IService<Catalog> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(CatalogDto dto);


    /**
     * 修改
     * @param catalogVo
     * @return
     */
    public ResponseResult update(CatalogVo catalogVo);



}
