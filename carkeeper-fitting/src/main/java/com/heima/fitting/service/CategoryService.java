package com.heima.fitting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.fitting.dtos.CategoryDto;
import com.heima.model.fitting.pojos.Category;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface CategoryService extends IService<Category> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(CategoryDto dto);

    /**
     * 新增
     * @param category
     * @return
     */
    public ResponseResult saveAndUpdate(Category category);


    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);


    /**
     * 查询所有配件类型
     * @return
     */
    public ResponseResult findAllType();

}
