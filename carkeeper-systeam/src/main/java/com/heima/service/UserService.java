package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.model.systeam.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {
    PageResponseResult find(UserDto userDto);

    ResponseResult insert(User user);

    ResponseResult updated(User user);

    ResponseResult del(Integer id);
}
