//package com.heima.shop.job;
//
//
//import com.heima.shop.service.StoreService;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Log4j2
//public class ShopAutoDeleteImages {
//
//    @Autowired
//    StoreService storeService;
//
//    @XxlJob("shopAutoDeleteImages")
//    public ReturnT<String> autoScanJob(String param) throws Exception {
//        log.info("自媒体文章审核调度任务开始执行....");
//
//        storeService.delPicture();
//
//        log.info("自媒体文章审核调度任务执行结束....");
//        return ReturnT.SUCCESS;
//    }
//
//}
