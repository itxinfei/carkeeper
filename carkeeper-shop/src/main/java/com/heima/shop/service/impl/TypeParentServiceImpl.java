package com.heima.shop.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.common.exception.CustException;
import com.heima.model.shop.pojos.TypeParentSon;
import com.heima.shop.mapper.SerivceTypeParentSonMapper;
import com.heima.shop.mapper.TypeParentMapper;
import com.heima.shop.mapper.TypeSonMapper;
import com.heima.shop.service.TypeParentService;
import com.heima.model.shop.dtos.TypeParentDto;
import com.heima.model.shop.pojos.TypeParent;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TypeParentServiceImpl extends ServiceImpl<TypeParentMapper, TypeParent> implements TypeParentService {

    @Autowired
    TypeSonMapper typeSonMapper;

    @Autowired
    SerivceTypeParentSonMapper serivceTypeParentSonMapper;

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(TypeParentDto dto) {
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
        LambdaQueryWrapper<TypeParent> wraaper = new LambdaQueryWrapper<>();

        // 分类编码
         if (StringUtils.isNotBlank(dto.getCode())) {
            wraaper.like(TypeParent::getCode, dto.getCode());
         }
         // 分类名称
         if (StringUtils.isNotBlank(dto.getName())) {
            wraaper.like(TypeParent::getName, dto.getName());
         }

        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return PageResponseResult.page(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param typeParent
     * @return
     */
    @Override
    public ResponseResult insert(TypeParent typeParent) {
        //1 参数检查
        if (typeParent == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        typeParent.setCreateTime(new Date());
        typeParent.setUpdateTime(new Date());
        //2 执行保存
        save(typeParent);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 修改
     * @param typeParent
     * @return
     */
    @Override
    public ResponseResult update(TypeParent typeParent) {

        //1.检查参数
        if(null == typeParent){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (typeParent.getId() == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        typeParent.setUpdateTime(new Date());
        //2.修改
        updateById(typeParent);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //如果父id查询到在中间表中存在则不能删除
        if (serivceTypeParentSonMapper.selectList(Wrappers.<TypeParentSon>lambdaQuery()
                .eq(TypeParentSon::getParentId, id)) != null) {
            CustException.cust(AppHttpCodeEnum.PARENTID_ISEXIST_ISSTATUS_ERROR);
        }


        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
