package com.heima.controller;

import com.heima.controllerApi.Systeam.UserControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.pojo.User;
import com.heima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Autowired
    UserService userService;
    @PostMapping("/find")
    @Override
    public ResponseResult find(@RequestBody UserDto userDto) {
        return userService.find (userDto);
    }
   @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody User user) {
        return userService.insert (user);
    }
   @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody User user) {
        return userService.updated (user);
    }
  @GetMapping("/del/{id}")
    @Override
    public ResponseResult del(@PathVariable("id") Integer id) {
        return userService.del (id);
    }
}
