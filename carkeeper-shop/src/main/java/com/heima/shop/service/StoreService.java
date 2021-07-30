package com.heima.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.shop.dtos.StoreAuthDto;
import com.heima.model.shop.dtos.StoreDto;
import com.heima.model.shop.pojos.Store;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface StoreService extends IService<Store> {


    /**
     * 主体审核
     * status 0：通过  1：失败 2:待审核
     * @param dto
     * @param dto
     * @return
     */
    ResponseResult updateStatus(short flag, StoreAuthDto dto);

    /**
     * 查询待审核主体的列表
     * @param dto
     * @return
     */
    ResponseResult findList(StoreDto dto);

    ResponseResult findByCode(String code);

//    /**
//     * 查询主体审核失败的列表
//     * @param dto
//     * @return
//     */
//    public ResponseResult findAuthFailList(StoreDto dto);


    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findStoreByPage(StoreDto dto);

    /**
     * 新增
     * @param store
     * @return
     */
    public ResponseResult insert(Store store);

    /**
     * 修改
     * @param store
     * @return
     */
    public ResponseResult update(Store store);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Long id);

    /**
     * 上传图片
     * @param multipartFile MultipartFile
     * @return ResponseResult
     */
    ResponseResult uploadPicture(MultipartFile multipartFile );

    ResponseResult updateStoreStatus(Long code,String status);


    /**
     * 图片删除
     * @return ResponseResult
     */
    ResponseResult delPicture(StoreDto dto);


    /**
     * 编辑回显
     * @param code Long
     * @return ResponseResult
     */
    ResponseResult getOneStore(Long code);

    /**
     *服务商数据库导出excel
     * @param response HttpServletResponse
     * @return ResponseResult
     * @throws IOException IOException
     */
    void loadServeExcel(HttpServletResponse response, StoreDto dto) throws IOException;

    /**
     *配件商数据库导出excel
     * @param response HttpServletResponse
     * @return ResponseResult
     * @throws IOException IOException
     */
    void loadPartExcel(HttpServletResponse response, StoreDto dto) throws IOException;

    ResponseResult loadExcel(HttpServletResponse response) throws IOException;
}
