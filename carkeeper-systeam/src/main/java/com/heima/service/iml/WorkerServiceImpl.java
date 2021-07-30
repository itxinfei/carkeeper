package com.heima.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.UserMapper;
import com.heima.mapper.WorkerMapper;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.dto.WorkerDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.model.systeam.pojo.User;
import com.heima.model.systeam.pojo.Worker;
import com.heima.service.UserService;
import com.heima.service.WorkerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {
    @Override
    public PageResponseResult find(WorkerDto workerDto) {
        //1.参数检测
        if(workerDto==null){
            return (PageResponseResult) PageResponseResult.errorResult (AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //分页参数检查
        workerDto.checkParam();

        //2.模糊分页查询
        IPage<Worker> page = new Page (workerDto.getPage(),workerDto.getSize());
        LambdaQueryWrapper<Worker> lambdaQueryWrapper = new LambdaQueryWrapper ();
        if(StringUtils.isNotBlank(workerDto.getName())){
            lambdaQueryWrapper.like (Worker::getName,workerDto.getName ());
        }
        IPage<Worker> result = page(page, lambdaQueryWrapper);

        //3.结果封装
        return new PageResponseResult(workerDto.getPage(),workerDto.getSize(),(int)result.getTotal(),result.getRecords ());
    }

    @Override
    public ResponseResult insert(Worker worker) {
        if(worker==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.保存
        save(worker);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult updated(Worker worker) {
        //1.检查参数
        if(null == worker || worker.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改
        updateById(worker);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult del(Integer id) {
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.判断当前频道是否存在
        Worker worker = getById(id);
        if(worker==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //3.删除频道
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
