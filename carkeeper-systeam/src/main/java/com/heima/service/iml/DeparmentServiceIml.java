package com.heima.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.DeparmentMapper;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.service.DeparmentService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

@Service
public class DeparmentServiceIml extends ServiceImpl<DeparmentMapper, SyDeparment> implements DeparmentService {
    @Override
    public PageResponseResult find(SyDeparmentDto syDeparmentDto) {
        //1.参数检测
        if(syDeparmentDto==null){
            return (PageResponseResult) PageResponseResult.errorResult (AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //分页参数检查
        syDeparmentDto.checkParam();

        //2.模糊分页查询
        IPage<SyDeparment> page = new Page (syDeparmentDto.getPage(),syDeparmentDto.getSize());
        LambdaQueryWrapper<SyDeparment> lambdaQueryWrapper = new LambdaQueryWrapper ();
        if(StringUtils.isNotBlank(syDeparmentDto.getName())){
            lambdaQueryWrapper.like (SyDeparment::getName,syDeparmentDto.getName ());
        }
        IPage<SyDeparment> result = page(page, lambdaQueryWrapper);

        //3.结果封装
        return new PageResponseResult(syDeparmentDto.getPage(),syDeparmentDto.getSize(),(int)result.getTotal(),result.getRecords ());
    }

    @Override
    public ResponseResult insert(SyDeparment syDeparment) {
        if(syDeparment==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.保存
        saveOrUpdate (syDeparment);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult updated(SyDeparment syDeparment) {
        //1.检查参数
        if(null == syDeparment || syDeparment.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改
        System.out.println (syDeparment);
        updateById (syDeparment);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult del(Integer id) {
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断当前频道是否存在
         SyDeparment syDeparment = getById(id);
        if(syDeparment==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //3.删除频道
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


}
