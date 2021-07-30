package com.heima.fitting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.fitting.dtos.BrandDto;
import com.heima.model.fitting.pojos.Brand;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Version: V1.0
 */
public interface BrandService extends IService<Brand> {

    /**
     * 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findPage(BrandDto dto);

    /**
     * 新增和修改
     * @param brand
     * @return
     */
    public ResponseResult saveAndUpdate(Brand brand);

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
    public ResponseResult fileUpLoad(MultipartFile file);

}
