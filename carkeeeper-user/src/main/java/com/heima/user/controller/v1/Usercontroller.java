package com.heima.user.controller.v1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.pojos.AdminUser;
import com.heima.user.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class Usercontroller {

    @Autowired
    AdminUserMapper adminUserMapper;

    @GetMapping("/findUserRealname/{adminUserId}")
    public ResponseResult getUserRealname(@PathVariable("adminUserId") Long adminUserId){
        AdminUser adminUser = adminUserMapper.selectById(adminUserId);
        return ResponseResult.okResult(adminUser.getUsername());
    }

}
