package com.heima.fitting.controller.v1;

import com.heima.apis.fitting.PropertyControllerApi;
import com.heima.fitting.service.PropertyService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.PropertyDto;
import com.heima.model.fitting.pojos.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/property")
public class PropertyController implements PropertyControllerApi {

    @Autowired
    PropertyService propertyService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody PropertyDto dto) {
        return propertyService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param property
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Property property) {
        return propertyService.saveAndUpdate(property);
    }

    /**
     * 修改
     * @param property
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Property property) {
        return propertyService.saveAndUpdate(property);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return propertyService.deleteById(id);
    }
}
