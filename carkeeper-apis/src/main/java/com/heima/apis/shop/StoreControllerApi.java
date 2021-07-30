package com.heima.apis.shop;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.shop.dtos.StoreAuthDto;
import com.heima.model.shop.dtos.StoreDto;
import com.heima.model.shop.pojos.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * store管理接口
 * @author
 *
 */
@Api(value = "store管理", tags = "store", description = "store管理API")
public interface StoreControllerApi {

    /**
     * 主体审核通过
     * @param dto
     * @return
     */
    public ResponseResult authPass(StoreAuthDto dto);

    /**
     * 主体审核未通过
     * @param dto
     * @return
     */
    public ResponseResult authFail(StoreAuthDto dto);

    /**
     * 查询待审核主体列表
     * @param dto
     * @return
     */
    public ResponseResult  findList(StoreDto dto);

//    /**
//     * 查询主体审核失败的列表
//     * @param dto
//     * @return
//     */
//    public ResponseResult findAuthFailList(StoreDto dto);

    /**
     * 根据主体编码进行查询
     * @param code
     * @return
     */
    ResponseResult findByCode(String code);


    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(StoreDto dto);

    /**
     * 新增
     * @param  store
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Store store);

    /**
     * 修改
     * @param  store
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Store store);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Long id);


    public ResponseResult delPicture(@RequestBody StoreDto dto);

    public ResponseResult uploadPicture(@RequestParam("multipartFile") MultipartFile multipartFile);

    public ResponseResult updateStoreStatus(@PathVariable("code") Long code, @PathVariable("status")String status);

    public ResponseResult getOneStore(@PathVariable("code")Long code);

    public void loadExcel(HttpServletResponse response, @RequestBody StoreDto dto) throws IOException;
}
