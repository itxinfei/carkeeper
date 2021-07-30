package com.heima.fitting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.fitting.pojos.Directory;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Description:
 * @Version: V1.0
 */
public interface DirectoryService extends IService<Directory> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(DirectoryDto dto);

    /**
     * 新增 和修改
     * @param directory
     * @return
     */
    public ResponseResult saveAndUpdate(Directory directory);


    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

}
