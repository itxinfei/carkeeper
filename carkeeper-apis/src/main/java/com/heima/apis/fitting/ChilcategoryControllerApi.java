package com.heima.apis.fitting;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.ChilcategoryDto;
import com.heima.model.fitting.pojos.Chilcategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

/**
 * chilcategory管理接口
 * @author author
 *
 */
@Api(value = "chilcategory管理", tags = "chilcategory", description = "chilcategory管理API")
public interface ChilcategoryControllerApi {



    ResponseResult fileUpLoad(MultipartFile file);

    /**
     * 列表
     * @param dto
     * @return
     */
    @ApiOperation("查询列表")
    public ResponseResult findPage(ChilcategoryDto dto);

    /**
     * 新增
     * @param  chilcategory
     * @return
     */
    @ApiOperation("新增")
    public ResponseResult save(Chilcategory chilcategory);

    /**
     * 修改
     * @param  chilcategory
     * @return
     */
    @ApiOperation("修改")
    public ResponseResult update(Chilcategory chilcategory);

    /**
     * 删除
     * @param id
     * @return
     */
    @ApiOperation("根据ID删除")
    public ResponseResult deleteById(Integer id);
}
