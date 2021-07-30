package com.heima.controller;

import com.heima.controllerApi.Systeam.DeparmentControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.service.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deparment")
public class DeparmentController implements DeparmentControllerApi {
    @Autowired
    DeparmentService deparmentService;
   @PostMapping("/find")
    @Override
    public ResponseResult find(@RequestBody SyDeparmentDto syDeparmentDto) {
        return deparmentService.find(syDeparmentDto);
    }
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody SyDeparment syDeparment) {

        return deparmentService.insert(syDeparment);
    }
    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody SyDeparment syDeparment) {
        System.out.println (syDeparment);
        return deparmentService.updated(syDeparment);
    }
    @GetMapping("/del/{id}")
    @Override
    public ResponseResult del(@PathVariable("id") Integer id) {
        return deparmentService.del(id);
    }


}
