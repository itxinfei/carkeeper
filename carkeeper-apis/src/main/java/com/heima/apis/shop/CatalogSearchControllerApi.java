package com.heima.apis.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.es.pojo.CatalogSearchDto;

public interface CatalogSearchControllerApi {
    /**
     *  搜索服务
     * @param
     * @return
     */
    public ResponseResult search(CatalogSearchDto catalogSearchDto);
}
