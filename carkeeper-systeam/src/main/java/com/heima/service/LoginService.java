package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.LoginDto;
import com.heima.model.systeam.pojo.LoginPojo;
import org.springframework.stereotype.Service;


public interface LoginService extends IService<LoginPojo> {

    ResponseResult login(LoginDto login);


}
