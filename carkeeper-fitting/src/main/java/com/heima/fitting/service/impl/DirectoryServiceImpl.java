package com.heima.fitting.service.impl;

import com.heima.fitting.mapper.DirectoryMapper;
import com.heima.fitting.service.DirectoryService;
import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.fitting.pojos.Directory;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class DirectoryServiceImpl extends ServiceImpl<DirectoryMapper, Directory> implements DirectoryService {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(DirectoryDto dto) {
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
        LambdaQueryWrapper<Directory> wraaper = new LambdaQueryWrapper<>();

        // 配件商
         if (StringUtils.isNotBlank(dto.getFittingMerchant())) {
            wraaper.like(Directory::getFittingMerchant, dto.getFittingMerchant());
         }

         //配件编码
        if(dto.getFittingCode() != null){
            wraaper.like(Directory::getFittingCode,dto.getFittingCode());
        }


        // 配件名称
         if (StringUtils.isNotBlank(dto.getFittingName())) {
            wraaper.like(Directory::getFittingName, dto.getFittingName());
         }
        // 规格型号
         if (StringUtils.isNotBlank(dto.getSpecification())) {
            wraaper.like(Directory::getSpecification, dto.getSpecification());
         }

        // 二级配件分类名字
         if (dto.getFittingChilCategoryName() != null) {
            wraaper.eq(Directory::getFittingChilCategoryName, dto.getFittingChilCategoryName());
         }

        // 配件品牌名字
         if (dto.getBrandName() != null) {
            wraaper.eq(Directory::getBrandName, dto.getBrandName());
         }


         //是否下架
         if (dto.getIsDown() != null){
             wraaper.eq(Directory::getIsDown, dto.getIsDown());
         }

        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveAndUpdate(Directory dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //TODO 新增时根据配件商代码 查看配件商是否存在


        if (dto.getId() == null)
            dto.setCreateTime(new Date());

        dto.setUpdateTime(new Date());

        //2 执行保存
        saveOrUpdate(dto);
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
