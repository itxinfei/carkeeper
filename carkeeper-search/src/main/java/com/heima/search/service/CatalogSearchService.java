package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.es.pojo.CatalogSearchDto;

public interface CatalogSearchService {

    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(CatalogSearchDto catalogSearchDto);
}
