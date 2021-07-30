package com.heima.fitting.controller.v1;

import com.heima.apis.fitting.BrandControllerApi;
import com.heima.fitting.service.BrandService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.BrandDto;
import com.heima.model.fitting.pojos.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController implements BrandControllerApi {

    @Autowired
    BrandService brandService;


    public ResponseResult fileUpLoad(@RequestParam MultipartFile multipartFile) {
        return brandService.fileUpLoad(multipartFile);
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody BrandDto dto) {
        return brandService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param brand
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Brand brand) {
        return brandService.saveAndUpdate(brand);
    }

    /**
     * 修改
     * @param brand
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Brand brand) {
        return brandService.saveAndUpdate(brand);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return brandService.deleteById(id);
    }
}
