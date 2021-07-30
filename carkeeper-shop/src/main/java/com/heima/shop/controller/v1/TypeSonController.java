package com.heima.shop.controller.v1;

import  com.heima.shop.service.TypeSonService;
import  com.heima.apis.shop.TypeSonControllerApi;
import  com.heima.model.shop.dtos.TypeSonDto;
import  com.heima.model.shop.pojos.TypeSon;
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
@RequestMapping("/api/v1/typeSon")
public class TypeSonController implements TypeSonControllerApi {

    @Autowired
    TypeSonService typeSonService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody TypeSonDto dto) {
        return typeSonService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param typeSon
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody TypeSon typeSon) {
        return typeSonService.insert(typeSon);
    }

    /**
     * 修改
     * @param typeSon
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody TypeSon typeSon) {
        return typeSonService.update(typeSon);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        return typeSonService.deleteById(id);
    }
}
