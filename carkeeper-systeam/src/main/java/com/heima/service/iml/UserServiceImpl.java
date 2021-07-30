package com.heima.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.UserMapper;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.model.systeam.pojo.User;
import com.heima.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public PageResponseResult find(UserDto userDto) {
        //1.参数检测
        if(userDto==null){
            return (PageResponseResult) PageResponseResult.errorResult (AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //分页参数检查
        userDto.checkParam();

        //2.模糊分页查询
        IPage<User> page = new Page (userDto.getPage(),userDto.getSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper ();
        lambdaQueryWrapper.eq (User::getIsDel,1);
        if(StringUtils.isNotBlank(userDto.getName())){
            lambdaQueryWrapper.like (User::getName,userDto.getName ());
        }
        IPage<User> result = page(page, lambdaQueryWrapper);

        //3.结果封装
        return new PageResponseResult(userDto.getPage(),userDto.getSize(),(int)result.getTotal(),result.getRecords ());
    }

    @Override
    public ResponseResult insert(User user) {
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        user.setIsDel (1);
        user.setIsMember (0);
        //2.保存
        save(user);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult updated(User user) {
        //1.检查参数
        if(null == user ||user.getId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改
        updateById(user);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult del(Integer id) {
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.
        User user = getById(id);
        if(user==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //3.0代表删除
        user.setIsDel (0);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
