package com.heima.fitting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.FileStorageService;
import com.heima.fitting.mapper.BrandMapper;
import com.heima.fitting.service.BrandService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.fitting.dtos.BrandDto;
import com.heima.model.fitting.pojos.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.web-site}")
    String webSite;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(BrandDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page page = new Page(dto.getPage(), dto.getSize());
        // 条件查询
        LambdaQueryWrapper<Brand> wraaper = new LambdaQueryWrapper<>();

        // 品牌名称
         if (StringUtils.isNotBlank(dto.getBrandName())) {
            wraaper.like(Brand::getBrandName, dto.getBrandName());
         }

        // 品牌编码
         if (dto.getBrandCode() != null) {
            wraaper.eq(Brand::getBrandCode, dto.getBrandCode());
         }


        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param brand
     * @return
     */
    @Override
    public ResponseResult saveAndUpdate(Brand brand) {
        //1 参数检查
        if (brand == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        if (brand.getId() == null){
            brand.setCreateTime(new Date());
        }
        brand.setUpdateTime(new Date());


        //2 执行保存
        save(brand);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }


    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


    /**
     * 图片上传
     *      LTAI4GJN6MV9vns2FYytcXf7
     *      wrn39wDurM6A81nMWVsLRmbHOuwV2Q
     * @param file
     * @return
     */
    @Override
    public ResponseResult fileUpLoad(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null)
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);


        String end = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        if(end.equals("jpg") || end.equals("img") || end.equals("png")){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "必须是 jpg img png 格式的图片");
        }


        String newFileName = UUID.randomUUID().toString().replace("-", "")+end;

        String fileUrl = null;
        try {
            fileUrl = fileStorageService.store("brand/images", newFileName, file.getInputStream());

            System.out.println(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseResult.okResult(webSite+fileUrl);
    }

}
