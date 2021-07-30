package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.dto.WorkerDto;
import com.heima.model.systeam.pojo.User;
import com.heima.model.systeam.pojo.Worker;
import lombok.Data;
import org.springframework.stereotype.Service;


public interface WorkerService extends IService<Worker> {
    PageResponseResult find(WorkerDto workerDto);

    ResponseResult insert(Worker worker);

    ResponseResult updated(Worker worker);

    ResponseResult del(Integer id);
}
