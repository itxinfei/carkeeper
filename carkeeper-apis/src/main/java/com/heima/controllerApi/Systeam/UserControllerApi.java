package com.heima.controllerApi.Systeam;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.systeam.dto.SyDeparmentDto;
import com.heima.model.systeam.dto.UserDto;
import com.heima.model.systeam.pojo.SyDeparment;
import com.heima.model.systeam.pojo.User;

public interface UserControllerApi {
    public ResponseResult find(UserDto userDto);

    /**
     * 新增
     * @param  user
     * @return
     */
    public ResponseResult save(User user);

    /**
     * 修改
     * @param user
     * @return
     */
    public ResponseResult update(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult del(Integer id);

}
