package com.heima.controller;

import com.heima.controllerApi.Systeam.LoginControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.LoginDto;
import com.heima.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
public class LoginController implements LoginControllerApi {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    @Override
    public ResponseResult login(@RequestBody LoginDto login) {
        return loginService.login(login);
    }
}
