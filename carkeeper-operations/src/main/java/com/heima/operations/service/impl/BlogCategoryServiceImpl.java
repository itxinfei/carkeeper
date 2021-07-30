package com.heima.operations.service.impl;

import com.heima.model.operations.pojos.Blog;
import com.heima.operations.mapper.BlogCategoryMapper;
import com.heima.operations.mapper.BlogMapper;
import com.heima.operations.service.BlogCategoryService;
import com.heima.common.exception.CustomException;
import com.heima.model.operations.dtos.BlogCategoryDto;
import com.heima.model.operations.pojos.BlogCategory;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public ResponseResult findAll() {
        List<String> blogCategoryList = blogCategoryMapper.findAll();
        return ResponseResult.okResult(blogCategoryList);
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(BlogCategoryDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行分页查询
        IPage<BlogCategory> page = new Page<>(dto.getPage(), dto.getSize());

        IPage<BlogCategory> pageResult = page(page);

        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增或修改
     * @param blogCategory
     * @return
     */
    @Override
    public ResponseResult saveBlogCategory(BlogCategory blogCategory) {
        //参数检查
        if (blogCategory == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //判断分类是否存在，不存在则新增，存在则修改
        BlogCategory category = getById(blogCategory.getId());
        if (category == null){
            //保存
            blogCategory.setCreateTime(new Date());
            save(blogCategory);
        }else {
            //更新

            blogCategory.setUpdateTime(new Date());
            updateById(blogCategory);
        }
        //返回结果
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Long id) {
        //检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //如果分类被引用则无法删除
        BlogCategory blogCategory = getById(id);
        if (blogCategory == null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        LambdaQueryWrapper<Blog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Blog::getBlogCategoryId,id);
        Integer count = blogMapper.selectCount(wrapper);
        if (count > 0){
            throw new CustomException(AppHttpCodeEnum.FILE_DELETE_ERROR);
        }
        //执行删除
        removeById(id);
        //返回结果
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
