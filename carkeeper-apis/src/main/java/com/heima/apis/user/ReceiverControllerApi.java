package com.heima.apis.user;

import com.heima.model.user.pojos.Receiver;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * receiver管理接口
 * @author hjl
 *
 */
@Api(value = "receiver管理", tags = "receiver", description = "receiver管理API")
public interface ReceiverControllerApi {

    /**
     * 查询收货方信息
     *
     * @param receiverId
     * @return
     */
    @ApiOperation("查询收货方信息")
    public Receiver findReceiver( Long receiverId);

}
