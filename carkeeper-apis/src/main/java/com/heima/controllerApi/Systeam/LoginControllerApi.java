package com.heima.controllerApi.Systeam;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.LoginDto;


public interface LoginControllerApi {
    ResponseResult login(LoginDto login);

}
