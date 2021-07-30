package com.heima.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.shop.dtos.StoreAuthDto;
import com.heima.model.shop.dtos.StoreDto;
import com.heima.model.shop.pojos.Store;
import com.heima.shop.mapper.StoreMapper;
import com.heima.shop.service.ShopImagsService;
import com.heima.shop.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
@Transactional
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {


    @Autowired
    StoreMapper storeMapper;

    /**
     * 主体审核
     * status: 0 待审核 1 通过 2 拒绝
     *
     * @param flag
     * @param dto
     * @return
     */
    @Override
    public ResponseResult updateStatus(short flag, StoreAuthDto dto) {
        //检查参数
        if (dto == null || dto.getCode() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询当前主体是否存在
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Store::getCode, dto.getCode());

        Store store = storeMapper.selectOne(wrapper);
        if (store == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //判断当前主体是否为未审核状态
        if (store.getFlag() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "该实体已审核通过");
        }
        if (store.getFlag() == 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "实体已经是审核未通过状态");
        }
        //更改状态
        store.setFlag(flag);
        //updateById(store);
        storeMapper.updateByCode(dto.getCode(), dto.getFlag());
        return ResponseResult.okResult(store);
    }

    @Override
    public ResponseResult findList(StoreDto dto) {
        //参数检查
        dto.checkParam();
        Page<Store> page = new Page<>(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper();
        if (dto.getName() != null) {
            wrapper.like(Store::getName, dto.getName());
        }
        if (dto.getCode() != null) {
            wrapper.eq(Store::getCode, dto.getCode());
        }
        if (dto.getType() != null) {
            wrapper.eq(Store::getType, dto.getType());
        }
        if (dto.getContactName() != null) {
            wrapper.eq(Store::getContactName, dto.getContactName());
        }
        wrapper.eq(Store::getFlag, dto.getFlag());
        wrapper.orderByDesc(Store::getCreateTime);

        IPage<Store> pageResult = page(page, wrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) pageResult.getTotal(), pageResult.getRecords());
        //responseResult.setHost(webSite);
        return responseResult;
    }

    @Override
    public ResponseResult findByCode(String code) {
        //参数检查
        if (code == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询主体对象
        Store store = storeMapper.selectOne(Wrappers.<Store>lambdaQuery().eq(Store::getCode, code)
                .eq(Store::getFlag, 2));
        if (store == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(store);
    }


    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.web-site}")
    String webSite;

    @Value("${file.oss.profix}")
    String profix;

    @Autowired
    ShopImagsService shopImagsService;

    /**
     * 查询所有主体
     * 分页显示
     *
     * @param dto StoreDto
     * @return ResponseResult
     */
    @Override
    public ResponseResult findStoreByPage(StoreDto dto) {
        if (dto == null)
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);

        dto.checkParam();

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        IPage<Store> page = new Page<>(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getSimpleName())) {
            wrapper.like(Store::getSimpleName, dto.getSimpleName());
        }
        if (dto.getCode() != null) {
            wrapper.eq(Store::getCode, dto.getCode());
        }
        if (StringUtils.isNotBlank(dto.getProvince())) {
            wrapper.eq(Store::getProvince, dto.getProvince());
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            wrapper.eq(Store::getCity, dto.getCity());
        }
        if (StringUtils.isNotBlank(dto.getCounty())) {
            wrapper.eq(Store::getCounty, dto.getCounty());
        }
        if (StringUtils.isNotBlank(dto.getContactPhone())) {
            wrapper.eq(Store::getContactPhone, dto.getContactPhone());
        }
        if (StringUtils.isNotBlank(dto.getIsChain())) {
            wrapper.eq(Store::getIsChain, dto.getIsChain());
        }
        if (StringUtils.isNotBlank(dto.getStatus())) {
            wrapper.eq(Store::getStatus, dto.getStatus());
        }
        if (StringUtils.isNotBlank(dto.getType())) {
            wrapper.eq(Store::getType, dto.getType());
        }
        if (StringUtils.isNotBlank(dto.getContactName())) {
            wrapper.like(Store::getContactName, dto.getContactName());
        }
        IPage<Store> resultPage = page(page, wrapper);

        return new PageResponseResult(dto.getPage(), dto.getSize(), (int) resultPage.getTotal(), resultPage.getRecords());
    }

    /**
     * 新增
     *
     * @param store Store
     * @return ResponseResult
     */
    @Override
    public ResponseResult insert(Store store) {
        //1 参数检查
        if (store == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getSimpleName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getContactName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getContactPhone())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getProvince())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getCity())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getCounty())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getAddress())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }


//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        //2 执行保存
        store.setCreateTime(new Date());

        //阿里云图片检测

        save(store);
        return ResponseResult.errorResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult update(Store store) {

        //1.检查参数
        if (null == store) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        if (StringUtils.isBlank(store.getName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getSimpleName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getContactName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getContactPhone())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getProvince())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getCity())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getCounty())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (StringUtils.isBlank(store.getAddress())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        //2.修改
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getCode, store.getCode());
        remove(wrapper);
        store.setId(0L);
        baseMapper.insert(store);
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

    /**
     * 上传图片
     *
     * @param multipartFile MultipartFile
     * @return ResponseResult
     */
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        try {
            String originalFilename = multipartFile.getOriginalFilename();

            String uuidName = UUID.randomUUID().toString().replace("-", "");
            String postFix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = uuidName + postFix;

            //oss上文件的路径
            String fileId = fileStorageService.store(profix, newFileName, multipartFile.getInputStream());
            if (fileId == null) {
                CustException.cust(AppHttpCodeEnum.FILE_UPLOAD_ERROR);
            }

//            ShopImags shopImags = new ShopImags();
//            shopImags.setImags(fileId);
//            shopImagsService.save(shopImags);

            ResponseResult result = ResponseResult.okResult(fileId);
            result.setHost(webSite);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("file upload oss error:{}", e);
            return ResponseResult.errorResult(AppHttpCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 图片删除
     *
     * @return ResponseResult
     */
    @Override
    public ResponseResult delPicture(StoreDto dto) {

//        List<Store> storeList = list();
//        List<String> imgs = storeList.stream().map(store -> store.getBusinessLicenseUrl()).collect(Collectors.toList());
//        List<String> collect = storeList.stream().map(store -> store.getCorporationObverseUrl()).collect(Collectors.toList());
//        List<String> collect1 = storeList.stream().map(store -> store.getCorporationBackUrl()).collect(Collectors.toList());
//        imgs.addAll(collect);
//        imgs.addAll(collect1);
//
//        if (imgs.size()>0){
//            for (String img : imgs) {
//                if (img == null){
//                    continue;
//                }
//                LambdaQueryWrapper<ShopImags> wrapper =  new LambdaQueryWrapper<>();
//                wrapper.eq(ShopImags::getImags,img );
//                ShopImags shopImags = shopImagsService.getOne(wrapper);
//                shopImagsService.removeById(shopImags.getId());
//            }
//        }
//        List<ShopImags> imagsList = shopImagsService.list();
//        List<String> imags = imagsList.stream().map(shopImags -> shopImags.getImags()).collect(Collectors.toList());
//        if (imags.size()>0){
//            for (String imag : imags) {
//                fileStorageService.delete(imag);
//            }
//        }
//        List<Integer> ids = imagsList.stream().map(shopImags -> shopImags.getId()).collect(Collectors.toList());
//        shopImagsService.removeByIds(ids);

        if (dto == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }

        if (StringUtils.isNotBlank(dto.getBusinessLicenseUrl())
        ) {
            String buss = dto.getBusinessLicenseUrl();
            fileStorageService.delete(buss);
        }
        if (StringUtils.isNotBlank(dto.getCorporationObverseUrl())) {
            String coro = dto.getCorporationObverseUrl();
            fileStorageService.delete(coro);
        }
        if (StringUtils.isNotBlank(dto.getCorporationBackUrl())) {
            String corb = dto.getCorporationBackUrl();
            fileStorageService.delete(corb);
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 更新主体状态
     *
     * @param code   Long
     * @param status String
     * @return ResponseResult
     */
    @Override
    public ResponseResult updateStoreStatus(Long code, String status) {
        if (code == null || StringUtils.isBlank(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getCode, code);
        Store store = getOne(wrapper);
        store.setStatus(status);
        LambdaQueryWrapper<Store> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(Store::getCode, code);
        update(store, wrapper1);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 编辑回显
     *
     * @param code Long
     * @return ResponseResult
     */
    @Override
    public ResponseResult getOneStore(Long code) {
        if (code == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//        }

        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getCode, code);
        Store store = getOne(wrapper);
        ResponseResult result = ResponseResult.okResult(store);
        result.setHost(webSite);
        return result;
    }

    /**
     * 数据库导出excel
     *
     * @param response HttpServletResponse
     * @return ResponseResult
     * @throws IOException IOException
     */
    @Override
    public void loadServeExcel(HttpServletResponse response, StoreDto dto) throws IOException {

//        User user = AdminThreadLocalUtils.getUser();
//        if (user == null) {
//            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
//        }

        List<Store> storeList = findAll(dto);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("服务商");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("汽修厂编码");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("汽修厂简称");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("是否连锁");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("所在区域");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        for (int i = 0; i < storeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Store store = storeList.get(i);
            row.createCell(0).setCellValue(store.getCode());
            row.createCell(1).setCellValue(store.getSimpleName());
            row.createCell(2).setCellValue(store.getContactName());
            row.createCell(3).setCellValue(store.getContactPhone());
            row.createCell(4).setCellValue(store.getIsChain());
            row.createCell(5).setCellValue(store.getAddress());
            row.createCell(6).setCellValue(store.getStatus());
        }

        output(response, wb);


    }

    /**
     * 配件商数据库导出excel
     *
     * @param response HttpServletResponse
     * @param dto      StoreDto
     * @return ResponseResult
     * @throws IOException IOException
     */
    @Override
    public void loadPartExcel(HttpServletResponse response, StoreDto dto) throws IOException {
        List<Store> storeList = findAll(dto);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("配件商");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("配件商编码");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("配件商简称");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("所在区域");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        for (int i = 0; i < storeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Store store = storeList.get(i);
            row.createCell(0).setCellValue(store.getCode());
            row.createCell(1).setCellValue(store.getSimpleName());
            row.createCell(2).setCellValue(store.getContactName());
            row.createCell(3).setCellValue(store.getContactPhone());
            row.createCell(4).setCellValue(store.getAddress());
            row.createCell(5).setCellValue(store.getStatus());
        }
        output(response, wb);

    }

    private void output(HttpServletResponse response, HSSFWorkbook hssfWorkbook) throws IOException {
        OutputStream output = response.getOutputStream();
        response.reset();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = df.format(new Date());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

        //response.setContentType("application/force-download;charset=ISO8859-1");
        response.setContentType("application/vnd.ms-excel;charset=ISO8859-1");
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");

        hssfWorkbook.write(output);
        output.close();
    }

    private List<Store> findAll(StoreDto dto) {
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(dto.getSimpleName())) {
            wrapper.like(Store::getSimpleName, dto.getSimpleName());
        }
        if (dto.getCode() != null) {
            wrapper.eq(Store::getCode, dto.getCode());
        }
        if (StringUtils.isNotBlank(dto.getProvince())) {
            wrapper.eq(Store::getProvince, dto.getProvince());
        }
        if (StringUtils.isNotBlank(dto.getCity())) {
            wrapper.eq(Store::getCity, dto.getCity());
        }
        if (StringUtils.isNotBlank(dto.getCounty())) {
            wrapper.eq(Store::getCounty, dto.getCounty());
        }
        if (StringUtils.isNotBlank(dto.getContactPhone())) {
            wrapper.eq(Store::getContactPhone, dto.getContactPhone());
        }
        if (StringUtils.isNotBlank(dto.getIsChain())) {
            wrapper.eq(Store::getIsChain, dto.getIsChain());
        }
        if (StringUtils.isNotBlank(dto.getStatus())) {
            wrapper.eq(Store::getStatus, dto.getStatus());
        }
        if (StringUtils.isNotBlank(dto.getType())) {
            wrapper.eq(Store::getType, dto.getType());
        }
        if (StringUtils.isNotBlank(dto.getContactName())) {
            wrapper.like(Store::getContactName, dto.getContactName());
        }
        return list(wrapper);
    }


    @Override
    public ResponseResult loadExcel(HttpServletResponse response) throws IOException {
        List<Store> storeList = list();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("配件商");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("配件商编码");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("配件商简称");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("所在区域");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        for (int i = 0; i < storeList.size(); i++) {
            row = sheet.createRow(i + 1);
            Store store = storeList.get(i);
            row.createCell(0).setCellValue(store.getCode());
            row.createCell(1).setCellValue(store.getSimpleName());
            row.createCell(2).setCellValue(store.getContactName());
            row.createCell(3).setCellValue(store.getContactPhone());
            row.createCell(4).setCellValue(store.getAddress());
            row.createCell(5).setCellValue(store.getStatus());
        }

        output(response, wb);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}