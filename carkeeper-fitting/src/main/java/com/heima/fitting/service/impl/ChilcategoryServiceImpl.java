package com.heima.fitting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.impl.OSSAliyunFileStorageService;
import com.heima.fitting.mapper.CategoryMapper;
import com.heima.fitting.mapper.ChilcategoryMapper;
import com.heima.fitting.mapper.DirectoryMapper;
import com.heima.fitting.service.ChilcategoryService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.fitting.dtos.ChilcategoryDto;
import com.heima.model.fitting.pojos.Category;
import com.heima.model.fitting.pojos.Chilcategory;
import com.heima.model.fitting.pojos.Directory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
@Slf4j
public class ChilcategoryServiceImpl extends ServiceImpl<ChilcategoryMapper, Chilcategory> implements ChilcategoryService {

    @Autowired
    DirectoryMapper directoryMapper;

    @Autowired
    OSSAliyunFileStorageService fileStorageService;

    @Autowired
    CategoryMapper categoryMapper;

    @Value("${file.oss.web-site}")
    String webSite;


    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(ChilcategoryDto dto) {
        //1 参数检查
        if (dto == null || dto.getCategoryCode() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page page = new Page(dto.getPage(), dto.getSize());
        // 条件查询
        LambdaQueryWrapper<Chilcategory> wraaper = new LambdaQueryWrapper<>();

        // 二级分类名称
        if (StringUtils.isNotBlank(dto.getChilCategoryName())) {
            wraaper.like(Chilcategory::getChilCategoryName, dto.getChilCategoryName());
        }
        // 二级分类编码
        if (dto.getChilCategoryCode() != null) {
            wraaper.eq(Chilcategory::getChilCategoryCode, dto.getChilCategoryCode());
        }


        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),pageResult.getRecords());
    }

    /**
     * 新增
     * @param chilcategory
     * @return
     */
    @Override
    public ResponseResult saveAndUpdate(Chilcategory chilcategory) {
        //1 参数检查
        if (chilcategory == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        if (categoryMapper.selectOne(new LambdaQueryWrapper<Category>().
                eq(Category::getCategoryCode, chilcategory.getCategoryCode())) == null)
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "一级分类已经不存在");


        if (chilcategory.getId() == null && getOne(new LambdaQueryWrapper<Chilcategory>()
                .eq(Chilcategory::getChilCategoryCode, chilcategory.getChilCategoryCode())) != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "已经有对应的二级分类编码了");
        }


        if (chilcategory.getChilCategoryCode() == null ||chilcategory.getChilCategoryCode() < chilcategory.getCategoryCode()*100
                ||chilcategory.getChilCategoryCode() > chilcategory.getCategoryCode()*100+99)
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,  "二级分类编码前6位必须和一级分类一致，必须为8位");


        if (chilcategory.getId() == null)
            chilcategory.setCreateTime(new Date());

        chilcategory.setUpdateTime(new Date());

        //2 执行保存
        saveOrUpdate(chilcategory);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }
    @Override
    public ResponseResult deleteById(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }



        List<Directory> directories = directoryMapper.selectList(new LambdaQueryWrapper<Directory>().eq(Directory::getFittingChilCategoryId, id));
        if (directories.size() > 0)
            return ResponseResult.errorResult(AppHttpCodeEnum.UNABLE_TO_DELETE, "此分类中还有配件无法删除");

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
            fileUrl = fileStorageService.store("chilCategory/images", newFileName, file.getInputStream());

            System.out.println(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseResult.okResult(webSite+fileUrl);
    }
}
