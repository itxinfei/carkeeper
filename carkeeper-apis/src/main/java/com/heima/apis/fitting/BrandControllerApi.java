package com.heima.apis.fitting;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.BrandDto;
import com.heima.model.fitting.pojos.Brand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * brand管理接口
 * @author author
 *
 */
@Api(value = "brand管理", tags = "brand", description = "brand管理API")
public interface BrandControllerApi {

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(BrandDto dto);

    /**
     * 新增
     * @param  brand
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Brand brand);

    /**
     * 修改
     * @param  brand
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Brand brand);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);


    /**
     * 文件上传（图片）
     * @param multipartFile
     * @return
     */
    public ResponseResult fileUpLoad(@RequestParam MultipartFile multipartFile);
}
