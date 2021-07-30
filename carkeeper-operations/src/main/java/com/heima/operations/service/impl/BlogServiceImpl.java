package com.heima.operations.service.impl;

import com.heima.feigns.fitting.FittingFeign;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.fitting.dtos.DirectoryDto;
import com.heima.model.operations.dtos.BlogDto;
import com.heima.model.operations.pojos.BlogComponent;
import com.heima.model.operations.vos.BlogVo;
import com.heima.operations.mapper.BlogMapper;
import com.heima.operations.service.BlogService;
import com.heima.model.operations.pojos.Blog;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;


/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    FittingFeign fittingFeign;

    /**
     * 添加配件查询列表
     *
     * @return
     */
    @Override
    public ResponseResult findComponentPage(@RequestBody DirectoryDto dto) {
        return fittingFeign.findPage(dto);
    }

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult findBlogById(Long id) {
        //检查参数
        if (id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //执行查询
        Blog blog = blogMapper.selectById(id);
        if (blog == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        String cover = blog.getCover();
        if (StringUtils.isNotBlank(cover)){
            String imgUrl = webSite + cover;
            blog.setCover(imgUrl);
        }
        //返回结果
        return ResponseResult.okResult(blog);
    }

    /**
     * 新增或修改
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveBlog(BlogDto dto) {
        //1 参数检查
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2 执行保存
        //属性拷贝
        Blog blog = new Blog();
        BeanUtils.copyProperties(dto,blog);
        //处理封面图片
        if (StringUtils.isNotBlank(dto.getCover())){
            String cover = imageToStr(dto.getCover(), webSite);
            blog.setCover(cover);
        }
        //如果文章不存在则新增，存在则修改
        if (blog.getId() == null){
            //保存
            blog.setCreateTime(new Date());
            save(blog);
            Long id = blog.getId();
            List<Long> componentIds = dto.getComponentIds();
            for (Long componentId : componentIds) {
                BlogComponent blogComponent = blogMapper.findBlogComponent(id, componentId);
                if (blogComponent == null){
                    blogMapper.addComponent(id,componentId);
                }
            }
        }else {
            //更新
            Long id = blog.getId();
            List<Long> componentIds = dto.getComponentIds();
            blogMapper.delComponent(id);
            for (Long componentId : componentIds) {
                BlogComponent blogComponent = blogMapper.findBlogComponent(id, componentId);
                if (blogComponent == null){
                    blogMapper.addComponent(id,componentId);
                }
            }
            blog.setUpdateTime(new Date());
            updateById(blog);
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 处理封面图片
     * @param image
     * @param webSite
     * @return
     */
    private String imageToStr(String image, String webSite) {
        return image.toString().replace(webSite,"");
    }

    /**
     * 发布或取消
     * @param dto
     * @return
     */
    @Override
    public ResponseResult isPublish(BlogDto dto) {
        //1.检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //先查
        Blog blog = getById(dto.getId());
        if (blog == null){
            return  ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        if (dto.getStatus() == 1 && blog.getStatus() == 1){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"不可重复取消");
        }
        if (dto.getStatus() == 0 && blog.getStatus() == 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"不可重复发布");
        }
        //执行修改
        if (dto.getStatus() == 0 && blog.getStatus() == 1){
            blog.setStatus((short) 0);
            updateById(blog);
        }
        if (dto.getStatus() == 1 && blog.getStatus() == 0){
            blog.setStatus((short) 1);
            updateById(blog);
        }
        //返回结果
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
