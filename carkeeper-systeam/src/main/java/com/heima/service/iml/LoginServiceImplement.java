package com.heima.service.iml;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.heima.mapper.LoginMapper;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.systeam.dto.LoginDto;
import com.heima.model.systeam.pojo.LoginPojo;
import com.heima.service.LoginService;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service
public class LoginServiceImplement extends ServiceImpl<LoginMapper, LoginPojo> implements LoginService {
    @Override
    public ResponseResult login(LoginDto login) {
        if (login==null || StringUtils.isBlank (login.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "用户名或密码不能为空");
        }
        LoginPojo loginPojo=getOne (Wrappers.<LoginPojo>lambdaQuery ().eq (LoginPojo::getName,login.getName ()));

        if (loginPojo == null) {
            return ResponseResult.errorResult (AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
        }

        String pswd = DigestUtils.md5DigestAsHex((login.getPassword() + loginPojo.getSalt()).getBytes());
        System.out.println (pswd);
        if (loginPojo.getPassword().equals(pswd)) {
            Map<String, Object> map = Maps.newHashMap();
            loginPojo.setPassword("");
            loginPojo.setSalt("");
            map.put("token", AppJwtUtil.getToken(loginPojo.getId ().longValue ()));
            map.put("user", loginPojo);
            return ResponseResult.okResult(map);
        }
        return ResponseResult.errorResult (AppHttpCodeEnum.DATA_NOT_EXIST);

    }


}
