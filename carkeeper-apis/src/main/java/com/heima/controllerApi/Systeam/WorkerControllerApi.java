package com.heima.controllerApi.Systeam;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.dto.WorkerDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.model.systeam.pojo.Worker;

public interface WorkerControllerApi {
    public ResponseResult find(WorkerDto workerDto);

    /**
     * 新增
     * @param  worker
     * @return
     */
    public ResponseResult save(Worker worker);

    /**
     * 修改
     * @param worker
     * @return
     */
    public ResponseResult update(Worker worker);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult del(Integer id);
}
