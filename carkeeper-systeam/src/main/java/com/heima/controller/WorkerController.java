package com.heima.controller;

import com.heima.controllerApi.Systeam.WorkerControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.WorkerDto;
import com.heima.model.systeam.pojo.Worker;
import com.heima.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class WorkerController implements WorkerControllerApi {
    @Autowired
    WorkerService workerService;
    @PostMapping("/find")
    @Override
    public ResponseResult find(@RequestBody WorkerDto workerDto) {
        return workerService.find (workerDto);
    }
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Worker worker) {
        return workerService.insert (worker);
    }
    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody Worker worker) {
        return workerService.updated (worker);
    }
    @GetMapping("/del/{id}")
    @Override
    public ResponseResult del(@PathVariable("id") Integer id) {
        return workerService.del (id);
    }
}
