package com.heima.shop.controller.v1;

import  com.heima.shop.service.TypeParentService;
import  com.heima.apis.shop.TypeParentControllerApi;
import  com.heima.model.shop.dtos.TypeParentDto;
import  com.heima.model.shop.pojos.TypeParent;
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
@RequestMapping("/api/v1/typeParent")
public class TypeParentController implements TypeParentControllerApi {

    @Autowired
    TypeParentService typeParentService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody TypeParentDto dto) {
        return typeParentService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param typeParent
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody TypeParent typeParent) {
        return typeParentService.insert(typeParent);
    }

    /**
     * 修改
     * @param typeParent
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody TypeParent typeParent) {
        return typeParentService.update(typeParent);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return typeParentService.deleteById(id);
    }
}
