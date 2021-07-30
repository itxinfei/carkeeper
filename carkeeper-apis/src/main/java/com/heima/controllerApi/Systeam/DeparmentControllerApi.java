package com.heima.controllerApi.Systeam;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.pojo.SyDeparment;
import io.swagger.annotations.ApiOperation;

public interface DeparmentControllerApi {

    public ResponseResult find(SyDeparmentDto syDeparmentDto);

    /**
     * 新增
     * @param  syDeparment
     * @return
     */
    public ResponseResult save(SyDeparment syDeparment);

    /**
     * 修改
     * @param syDeparment
     * @return
     */
    public ResponseResult update(SyDeparment syDeparment);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult del(Integer id);


}
