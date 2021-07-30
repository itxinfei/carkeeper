package com.heima.operations.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.model.operations.vos.ArticleVo;
import com.heima.operations.mapper.ArticleMapper;
import com.heima.operations.service.ArticleService;
import com.heima.common.exception.CustomException;
import com.heima.model.operations.dtos.ArticleDto;
import com.heima.model.operations.pojos.Article;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    ArticleMapper articleMapper;

    /**
     * 查询列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPage(ArticleDto dto) {
        //参数检查
        if (dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        //模糊查询
        if (StringUtils.isNotBlank(dto.getName())){
            dto.setName("%" + dto.getName() + "%");
        }
        //处理分页起始索引
        Integer page = dto.getPage();
        int from = (page - 1) * dto.getSize();
        dto.setPage(from);
        //执行查询
        List<ArticleVo> articleVos = articleMapper.findPage(dto);
        int count = articleMapper.findCount(dto);
        //返回结果
        PageResponseResult result = new PageResponseResult(page, dto.getSize(), count, articleVos);
        result.setHost(webSite);
        return result;
    }

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveArticle(ArticleDto dto) {
        //参数检查
        if (dto == null || StringUtils.isBlank(dto.getContent())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //执行保存
        //属性拷贝
        Article article = new Article();
        BeanUtils.copyProperties(dto,article);
        //处理推文标题图片
        if (StringUtils.isNotBlank(dto.getImage())){
            String img = imageToStr(dto.getImage(), webSite);
            article.setImage(img);
        }

        //查询推文是否存在，不存在则新增，存在则修改
        if (article.getId() == null){
            //保存
            article.setCreateTime(new Date());
            save(article);
        }else {
            //更新
            article.setUpdateTime(new Date());
            updateById(article);
        }
        //返回结果
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 处理标题图片
     * @param image
     * @param webSite
     * @return
     */
    private String imageToStr(String image, String webSite) {
        image = image.toString().replace(webSite,"");
        return image;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Long id) {
        //检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        Article article = getById(id);
        if (article == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }

        //执行删除
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
