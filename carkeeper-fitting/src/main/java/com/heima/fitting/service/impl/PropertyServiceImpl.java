package com.heima.fitting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.fitting.mapper.PropertyMapper;
import com.heima.fitting.service.PropertyService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.fitting.dtos.PropertyDto;
import com.heima.model.fitting.pojos.Property;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(PropertyDto dto) {
        //1 参数检查
        if (dto == null || dto.getChilCategoryId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page page = new Page(dto.getPage(), dto.getSize());
        // 条件查询
        LambdaQueryWrapper<Property> wraaper = new LambdaQueryWrapper<>();
        wraaper.eq(Property::getChilCategoryId, dto.getChilCategoryId());

        IPage pageResult = page(page, wraaper);
        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param property
     * @return
     */
    @Override
    public ResponseResult saveAndUpdate(Property property) {
        //1 参数检查 //可选项，属性名称不能为空不能为空
        if (property == null || property.getTabOption() == null || property.getAttributeName() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //表单类型校验
        if (property.getFromType() == null){
            property.setFromType(1);
        }

        //默认参数不存在校验
        if (property.getDefaultOption() == null){
            if (property.getFromType() == 1 || property.getFromType() == 5)
                property.setDefaultOption(property.getTabOption());

            if (property.getFromType() != 1 || property.getFromType() != 5)
                property.setDefaultOption(property.getTabOption().substring(0,property.getTabOption().indexOf("@")));
        }

        //默认参数不合法检验
        if (!property.getTabOption().contains(property.getDefaultOption())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "默认选项不在选项中");
        }

        //是否必须检验
        if (property.getIsMust() == null){
            property.setIsMust((byte) 1);
        }

        //是否搜索校验
        if (property.getIsSearch() == null){
            property.setIsSearch((byte) 1);
        }


        if (property.getId() == null)
            property.setCreateTime(new Date());

        property.setUpdateTime(new Date());


        //2 执行保存
        saveOrUpdate(property);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
