package com.heima.fitting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.fitting.dtos.ChilcategoryDto;
import com.heima.model.fitting.pojos.Chilcategory;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Version: V1.0
 */
public interface ChilcategoryService extends IService<Chilcategory> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(ChilcategoryDto dto);

    /**
     * 新增 和 修改
     * @param chilcategory
     * @return
     */
    public ResponseResult saveAndUpdate(Chilcategory chilcategory);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

    /**
     * 图片上传
     * @param file
     * @return
     */
    ResponseResult fileUpLoad(MultipartFile file);
}
