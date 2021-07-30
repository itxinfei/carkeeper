package com.heima.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.model.common.constants.ArticleConstants;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.shop.dtos.TypeSonDto;
import com.heima.model.shop.pojos.Catalog;
import com.heima.model.shop.pojos.TypeParent;
import com.heima.model.shop.pojos.TypeParentSon;
import com.heima.model.shop.pojos.TypeSon;
import com.heima.shop.mapper.CatalogMapper;
import com.heima.shop.mapper.SerivceTypeParentSonMapper;
import com.heima.shop.mapper.TypeParentMapper;
import com.heima.shop.mapper.TypeSonMapper;
import com.heima.shop.service.TypeSonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class TypeSonServiceImpl extends ServiceImpl<TypeSonMapper, TypeSon> implements TypeSonService {


    @Autowired
    TypeSonMapper typeSonMapper;

    @Autowired
    CatalogMapper catalogMapper;

    @Autowired
    TypeParentMapper typeParentMapper;

    @Autowired
    SerivceTypeParentSonMapper serivceTypeParentSonMapper;

    /**
     * 查询列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(TypeSonDto dto) {
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
        LambdaQueryWrapper<TypeSon> wraaper = new LambdaQueryWrapper<>();
        //根据前端点击子分类时进行全部查询
        //根据父id查询所有属于该父分类的子分类 =========== 父id必须传
        if (dto.getParentId() == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        wraaper.eq(TypeSon::getParentId, dto.getParentId());

        // 子分类名称
        if (StringUtils.isNotBlank(dto.getName())) {
            wraaper.like(TypeSon::getName, dto.getName());
        }
        // 子分类编号
        if (StringUtils.isNotBlank(dto.getCode())) {
            wraaper.like(TypeSon::getCode, dto.getCode());
        }

        IPage pageResult = page(page, wraaper);

        //3 封装分页结果
        return PageResponseResult.page(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(), pageResult.getRecords());
    }

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * 新增
     *
     * @param typeSon
     * @return
     */
    @Override
    public ResponseResult insert(TypeSon typeSon) {
        //1 参数检查
        if (typeSon == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        LambdaQueryWrapper<TypeSon> wraaper = new LambdaQueryWrapper<>();
        wraaper.eq(TypeSon::getCode, typeSon.getCode());

        if (typeSonMapper.selectList(wraaper).size() > 0) {
            CustException.cust(AppHttpCodeEnum.CODE_EXIST);
        }

        typeSon.setCreateTime(new Date());
        typeSon.setUpdateTime(new Date());
        //2 执行保存
        save(typeSon);

        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult update(TypeSon typeSon) {

        //1.检查参数
        if (null == typeSon || typeSon.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        typeSon.setUpdateTime(new Date());
        updateById(typeSon);


        //导入数据到es索引库  发送消息
//        TODO ====== 先通过父id在子分类中查询出父分类的服务名称
        TypeParent typeParent = typeParentMapper.selectOne(Wrappers.<TypeParent>lambdaQuery()
                .eq(TypeParent::getId, typeSon.getParentId()));
        String parentName = typeParent.getName();
//         * 然后把子分类中修改过的信息查出来
        TypeSon updateTypeSon = getById(typeSon.getId());
        String sonCode = updateTypeSon.getCode();
        String sonName = updateTypeSon.getName();
//         * 再将目录里面的信息全部查出来放到list集合中
        //先通过子id查询出中间表id，再通过中间表id查询目录信息放到集合中
        TypeParentSon typeParentSon = serivceTypeParentSonMapper.selectOne(Wrappers.<TypeParentSon>lambdaQuery()
                .eq(TypeParentSon::getSonId, typeSon.getId()));
        Long id = typeParentSon.getId();
        List<Catalog> catalogs = catalogMapper.selectList(Wrappers.<Catalog>lambdaQuery()
                .eq(Catalog::getParentSonId, id));
//         * 然后遍历集合的同时将信息一一对应的放到Vo中并提交给索引库
        for (Catalog catalog : catalogs) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", catalog.getId());
            map.put("name", catalog.getName());
            map.put("parentName", parentName);
            map.put("sonCode", sonCode);
            map.put("sonName", sonName);
            map.put("price", catalog.getPrice());
            map.put("enable", catalog.getEnable());
            map.put("special", catalog.getSpecial());
            // 发送消息 给 search服务同步索引库
            kafkaTemplate.send(ArticleConstants.ARTICL_ES_SYNC_TOPIC_SIR, JSON.toJSONString(map));
        }



        // TODO ==========================================
//        LambdaQueryWrapper<TypeSon> wraaper = new LambdaQueryWrapper<>();
//
//        wraaper.eq(TypeSon::getCode, typeSon.getCode());
//
//        if (typeSonMapper.selectList(wraaper).size() > 1) {
//
//            CustException.cust(AppHttpCodeEnum.CODE_EXIST);
//        }
//        if (typeSonMapper.selectList(Wrappers.<TypeSon>lambdaQuery()
//                .eq(TypeSon::getName, typeSon.getName())).size() > 1) {
//            CustException.cust(AppHttpCodeEnum.NAME_EXIST);
//        }

        //2.修改

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult deleteById(Long id) {
        //1.检查参数
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
