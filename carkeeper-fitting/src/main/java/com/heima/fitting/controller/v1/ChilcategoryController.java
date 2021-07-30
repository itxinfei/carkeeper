package com.heima.fitting.controller.v1;

import com.heima.apis.fitting.ChilcategoryControllerApi;
import com.heima.fitting.service.ChilcategoryService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.ChilcategoryDto;
import com.heima.model.fitting.pojos.Chilcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/chilcategory")
public class ChilcategoryController implements ChilcategoryControllerApi {

    @Autowired
    ChilcategoryService chilcategoryService;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("/fileUpLoad")
    @Override
    public ResponseResult fileUpLoad(@RequestParam("multipartFile") MultipartFile file) {
        return chilcategoryService.fileUpLoad(file);
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody ChilcategoryDto dto) {
        return chilcategoryService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param chilcategory
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Chilcategory chilcategory) {
        return chilcategoryService.saveAndUpdate(chilcategory);
    }

    /**
     * 修改
     * @param chilcategory
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Chilcategory chilcategory) {
        return chilcategoryService.saveAndUpdate(chilcategory);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return chilcategoryService.deleteById(id);
    }
}
