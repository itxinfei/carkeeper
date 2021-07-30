package com.heima.operations.service.impl;

import com.heima.common.exception.CustException;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.operations.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.web-site}")
    String webSite;

    @Value("${file.oss.profix}")
    String profix;

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //1 检查参数
        if (multipartFile.isEmpty()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2 文件上传到 oss
        try {
            // 文件重命名
            // 原始文件名称
            String originalFilename = multipartFile.getOriginalFilename();

            // 新文件名称
            String uuidName = UUID.randomUUID().toString().replace("-", "");
            String postFix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = uuidName + postFix;

            String fileId = fileStorageService.store(profix, newFileName, multipartFile.getInputStream());
            if (StringUtils.isBlank(fileId)) {
                CustException.cust( AppHttpCodeEnum.FILE_UPLOAD_ERROR );
            }
            String url = webSite + fileId;
            return ResponseResult.okResult(url);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("file upload oss error:{}", e);
        }

        return ResponseResult.errorResult(AppHttpCodeEnum.FILE_UPLOAD_ERROR);
    }

}
