package com.heima.shop.controller.v1;

import com.heima.apis.shop.StoreControllerApi;
import com.heima.common.exception.CustException;
import com.heima.model.common.constants.shop.StoreConstants;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.shop.dtos.StoreAuthDto;
import com.heima.model.shop.dtos.StoreDto;
import com.heima.model.shop.pojos.Store;
import com.heima.shop.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/api/v1/shop")
@Api("主体管理")
public class StoreControlelr implements StoreControllerApi {

    @Autowired
    StoreService storeService;


    /**
     * 主体审核通过
     *
     * @param dto
     * @return
     */
    @Override
    @PostMapping("/authPass")
    @ApiOperation("主体审核通过")
    public ResponseResult authPass(@RequestBody StoreAuthDto dto) {
        return storeService.updateStatus(StoreConstants.STORE_AUTH_PASS, dto);
    }

    /**
     * 主体审核未通过
     *
     * @param dto
     * @return
     */
    @Override
    @PostMapping("/authFail")
    @ApiOperation("主体审核失败")
    public ResponseResult authFail(@RequestBody StoreAuthDto dto) {
        return storeService.updateStatus(StoreConstants.STORE_AUTH_FAIL, dto);
    }

    /**
     * 查询待审核主体列表
     *
     * @param dto
     * @return
     */
    @Override
    @PostMapping("/findList")
    @ApiOperation("查询主体列表")
    public ResponseResult findList(@RequestBody StoreDto dto) {
        return storeService.findList(dto);
    }

    /**
     * 根据主体编码进行查询
     *
     * @param code
     * @return
     */
    @Override
    @GetMapping("/{id}")
    @ApiOperation("根据code查询主体")
    public ResponseResult findByCode(@PathVariable("id") String code) {
        return storeService.findByCode(code);
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    /**
     * 查询列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    @ApiOperation("主体条件分页查询")
    public ResponseResult findPage(@RequestBody StoreDto dto) {
        return storeService.findStoreByPage(dto);
    }

    /**
     * 新增
     *
     * @param store
     * @return
     */
    @PostMapping("/save")
    @Override
    @ApiOperation("主体新增")
    public ResponseResult save(@RequestBody Store store) {
        return storeService.insert(store);
    }

    /**
     * 修改
     *
     * @param store
     * @return
     */
    @Override
    @PostMapping("/update")
    @ApiOperation("主体更新")
    public ResponseResult update(@RequestBody Store store) {
        return storeService.update(store);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Long id) {
        return storeService.deleteById(id);
    }

    /**
     * 图片上上传
     *
     * @param multipartFile MultipartFile
     * @return ResponseResult
     */
    @PostMapping("/upload_picture")
    @Override
    @ApiOperation("上传图片")
    public ResponseResult uploadPicture(@RequestParam("multipartFile") MultipartFile multipartFile) {
        return storeService.uploadPicture(multipartFile);
    }

    @PostMapping("/delPicture")
    @Override
    @ApiOperation("删除图片")
    public ResponseResult delPicture(@RequestBody StoreDto dto) {
        return storeService.delPicture(dto);
    }

    /**
     * 修改主体状态
     *
     * @param code   Long
     * @param status String
     * @return ResponseResult
     */
    @GetMapping("/status/{code}/{status}")
    @Override
    @ApiOperation("更新主体状态")
    public ResponseResult updateStoreStatus(@PathVariable("code") Long code, @PathVariable("status") String status) {
        return storeService.updateStoreStatus(code, status);
    }

    /**
     * 编辑回显
     *
     * @param code Long
     * @return ResponseResult
     */
    @GetMapping("/status/{code}")
    @Override
    @ApiOperation("编辑回显")
    public ResponseResult getOneStore(@PathVariable("code") Long code) {
        return storeService.getOneStore(code);
    }

    @PostMapping("/loadExcel")
    @Override
    @ApiOperation(value = "导出excel")
    public void loadExcel(HttpServletResponse response, @RequestBody StoreDto dto) throws IOException {
        if (dto.getType().equals(StoreConstants.SEVER_THEME)) {
           storeService.loadServeExcel(response, dto);

        } else if (dto.getType().equals(StoreConstants.PARTS_THEME)) {
           storeService.loadPartExcel(response, dto);

        } else {
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

    }



    @GetMapping("/excel")
    public ResponseResult loadExcel2(HttpServletResponse response) {
        try {
            return storeService.loadExcel(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
    }

}
