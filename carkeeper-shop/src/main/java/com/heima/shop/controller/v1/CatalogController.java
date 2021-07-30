package com.heima.shop.controller.v1;

import com.heima.model.es.pojo.CatalogVo;
import  com.heima.shop.service.CatalogService;
import  com.heima.apis.shop.CatalogControllerApi;
import  com.heima.model.shop.dtos.CatalogDto;
import  com.heima.model.shop.pojos.Catalog;
import  com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController implements CatalogControllerApi {

    @Autowired
    CatalogService catalogService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody CatalogDto dto) {
        return ResponseResult.okResult(catalogService.findPage(dto));
    }

    /**
     * 修改
     * @param catalogVo
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody CatalogVo catalogVo) {
        return catalogService.update(catalogVo);
    }


}
