package com.heima.fitting.controller.v1;

import com.heima.apis.fitting.CategoryControllerApi;
import com.heima.fitting.service.CategoryService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.CategoryDto;
import com.heima.model.fitting.pojos.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController implements CategoryControllerApi {

    @Autowired
    CategoryService categoryService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody CategoryDto dto) {
        return categoryService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param category
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Category category) {
        return categoryService.saveAndUpdate(category);
    }

    /**
     * 修改
     * @param category
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Category category) {
        return categoryService.saveAndUpdate(category);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return categoryService.deleteById(id);
    }


    /**
     * 查询所有配件类型
     *
     * @return
     */
    @Override
    @GetMapping("findAllType")
    public ResponseResult findAllType() {
        return categoryService.findAllType();
    }
}
