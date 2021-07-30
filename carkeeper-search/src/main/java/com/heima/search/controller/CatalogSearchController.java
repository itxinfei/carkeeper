package com.heima.search.controller;

import com.heima.apis.shop.CatalogControllerApi;
import com.heima.apis.shop.CatalogSearchControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.es.pojo.CatalogSearchDto;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.model.shop.pojos.Catalog;
import com.heima.search.service.CatalogSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
public class CatalogSearchController implements CatalogSearchControllerApi {

    @Autowired
    CatalogSearchService catalogSearchService;

    /**
     * 搜索服务
     *
     * @param catalogSearchDto@return
     */
    @PostMapping("/search")
    @Override
    public ResponseResult search(@RequestBody CatalogSearchDto catalogSearchDto) {

        return catalogSearchService.search(catalogSearchDto);
    }
}
