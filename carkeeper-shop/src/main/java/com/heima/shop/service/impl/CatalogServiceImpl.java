package com.heima.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.es.pojo.CatalogVo;
import com.heima.model.shop.dtos.CatalogDto;
import com.heima.model.shop.pojos.Catalog;
import com.heima.shop.mapper.CatalogMapper;
import com.heima.shop.mapper.TypeParentMapper;
import com.heima.shop.mapper.TypeSonMapper;
import com.heima.shop.service.CatalogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, Catalog> implements CatalogService {

    @Autowired
    TypeParentMapper typeParentMapper;

    @Autowired
    TypeSonMapper typeSonMapper;

    public List<CatalogVo> findCatalog( CatalogDto dto){
        dto.checkParam();
        return baseMapper.findCatalog(dto,(dto.getPage()-1)*dto.getSize(),dto.getSize());
    }

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(CatalogDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page<Catalog> page = new Page(dto.getPage(), dto.getSize());

        // 条件查询
        LambdaQueryWrapper<Catalog> wraaper = new LambdaQueryWrapper<>();

        // 服务商名称
        if (StringUtils.isNotBlank(dto.getName())) {
            wraaper.like(Catalog::getName, dto.getName());
        }

        IPage<Catalog> pageResult = page(page, wraaper);
        List<Catalog> catalogs = pageResult.getRecords();

        CatalogVo catalogVo = new CatalogVo();
        List<CatalogVo> catalogVoList=new ArrayList<>();

//        for (Catalog catalog : catalogs) {
//            BeanUtils.copyProperties(catalog,catalogVo);
//            catalogVo.set;
//            catalogVoList.add(catalogVo);
//        }

//        catalogVoList = catalogs.stream().map(catalog -> {
//            CatalogVo catalogVo1 = new CatalogVo();
//            BeanUtils.copyProperties(catalog, catalogVo1);
//            catalogVo1.setTypeParentName();
//            return catalogVo1;
//        }).collect(Collectors.toList());

        //3 封装分页结果
        return PageResponseResult.page(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(),catalogVoList);
    }

    @Autowired
    CatalogService catalogService;
    /**
     * 上下架
     * @param catalogVo
     * @return
     */
    @Override
    public ResponseResult update(CatalogVo catalogVo) {
               //判断参数对象 对象里的重要数据 尽量为包装类或变量 不为空 且数据区间符合
//        if(catalogVo==null||catalogVo.getEnable()==null||catalogVo.getEnable()<0||catalogVo.getEnable()>1){
//            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
//        }

        //1.检查参数
        if(null == catalogVo){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //获取目录
        Catalog catalog1 = getById(catalogVo.getId());
        if (null == catalog1) {
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

//        if (catalogVo.getEnable()==0&&catalog1.getEnable()==0){
//           return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"已经下架，无需下架");
//        }
//        if (catalogVo.getEnable()==1&&catalog1.getEnable()==1){
//            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"已经上架，无需上架");
//        }
//        //下架
//        if (catalog1.getEnable() == (short)1&&catalogVo.getEnable()==0) {
//            catalog1.setEnable((short)0);
//            updateById(catalog1);
//            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
//        }
//        //上架
//        if (catalog1.getEnable()==0&&catalogVo.getEnable()==1){
//            catalog1.setEnable((short)1);
//            updateById(catalog1);
//            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
//        }
//        return ResponseResult.errorResult(AppHttpCodeEnum.MANUAL_CHECK,"需要联系管理员");

        //2.修改
        if (catalog1.getEnable() == (short)1) {
            catalog1.setEnable((short)0);
            updateById(catalog1);
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        catalog1.setEnable((short)1);
        updateById(catalog1);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
