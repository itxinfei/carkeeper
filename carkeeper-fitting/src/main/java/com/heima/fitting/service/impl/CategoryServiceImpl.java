package com.heima.fitting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.fitting.mapper.CategoryMapper;
import com.heima.fitting.mapper.ChilcategoryMapper;
import com.heima.fitting.mapper.TypeMapper;
import com.heima.fitting.service.CategoryService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.fitting.dtos.CategoryDto;
import com.heima.model.fitting.pojos.Category;
import com.heima.model.fitting.pojos.Chilcategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    ChilcategoryMapper chilcategoryMapper;

    @Autowired
    TypeMapper typeMapper;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(CategoryDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page page = new Page(dto.getPage(), dto.getSize());
        // 条件查询
        LambdaQueryWrapper<Category> wraaper = new LambdaQueryWrapper<>();

        // 配件分类名称
        if (StringUtils.isNotBlank(dto.getCategoryName())) {
            wraaper.like(Category::getCategoryName, dto.getCategoryName());
        }
        // 类型
        if (StringUtils.isNotBlank(dto.getType())) {
            wraaper.eq(Category::getType, dto.getType());
        }

        // 配件分类编码
        if (dto.getCategoryCode() != null) {
            wraaper.eq(Category::getCategoryCode, dto.getCategoryCode());
        }


        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param category
     * @return
     */
    @Override
    public ResponseResult saveAndUpdate(Category category) {
        //1 参数检查
        if (category == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //检查编码规则
        if (category.getCategoryCode() < 100000 || category.getCategoryCode() >999999){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"配件分类编码必须为6位");
        }


        //如果是新增，查询编码是否存在
        if (category.getId() == null){
            Category one = getOne(new LambdaQueryWrapper<Category>().eq(Category::getCategoryCode, category.getCategoryCode()));
            if (one != null)
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "编码已经存在");


            category.setCteateTime(new Date());
        }

        category.setUpdateTime(new Date());


        //2 执行保存
        saveOrUpdate(category);

        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        Category category = getById(id);
        List<Chilcategory> chilcategories = chilcategoryMapper.selectList(new LambdaQueryWrapper<Chilcategory>().eq(Chilcategory::getCategoryCode, category.getCategoryCode()));
        if (chilcategories.size() > 0)
            return ResponseResult.errorResult(AppHttpCodeEnum.UNABLE_TO_DELETE,"必须先删除所有的二级分类才能删除");

        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 查询所有配件类型
     *
     * @return
     */
    @Override
    public ResponseResult findAllType() {
        return ResponseResult.okResult(typeMapper.findAllType());
    }
}
