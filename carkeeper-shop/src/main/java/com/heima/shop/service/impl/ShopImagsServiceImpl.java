package com.heima.shop.service.impl;

import com.heima.model.store.dtos.ShopImagsDto;
import com.heima.model.store.pojos.ShopImags;
import com.heima.shop.mapper.ShopImagsMapper;
import com.heima.shop.service.ShopImagsService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class ShopImagsServiceImpl extends ServiceImpl<ShopImagsMapper, ShopImags> implements ShopImagsService {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(ShopImagsDto dto) {
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
        LambdaQueryWrapper<ShopImags> wraaper = new LambdaQueryWrapper<>();

        // imags
         if (StringUtils.isNotBlank(dto.getImags())) {
            wraaper.like(ShopImags::getImags, dto.getImags());
         }

        // id
         if (dto.getId() != null) {
            wraaper.eq(ShopImags::getId, dto.getId());
         }



        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return  new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param shopImags
     * @return
     */
    @Override
    public ResponseResult insert(ShopImags shopImags) {
        //1 参数检查
        if (shopImags == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2 执行保存
        save(shopImags);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult update(ShopImags shopImags) {

        //1.检查参数
        if(null == shopImags){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改
        updateById(shopImags);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
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
