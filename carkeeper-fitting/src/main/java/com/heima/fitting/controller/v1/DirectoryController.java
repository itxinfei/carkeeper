package com.heima.fitting.controller.v1;

import com.heima.apis.fitting.DirectoryControllerApi;
import com.heima.fitting.service.DirectoryService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.fitting.pojos.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Description:
 * @Version: V1.0
 */
@RestController
@RequestMapping("/api/v1/directory")
public class DirectoryController implements DirectoryControllerApi {

    @Autowired
    DirectoryService directoryService;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @PostMapping("list")
    @Override
    public ResponseResult findPage(@RequestBody DirectoryDto dto) {
        return directoryService.findPage(dto);
    }

    /**
     * 新增
     *
     * @param directory
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody Directory directory) {
        return directoryService.saveAndUpdate(directory);
    }

    /**
     * 修改
     * @param directory
     * @return
     */
    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Directory directory) {
        return directoryService.saveAndUpdate(directory);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return directoryService.deleteById(id);
    }
}
