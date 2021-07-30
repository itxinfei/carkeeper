package com.heima.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.feigns.shop.ShopFeign;
import com.heima.feigns.user.UserFeign;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.order.dtos.OrderDto;
import com.heima.model.order.dtos.PartAndOrderDto;
import com.heima.model.order.dtos.ServiceAndOrderDto;
import com.heima.model.order.pojos.Order;
import com.heima.model.order.pojos.PartAndOrder;
import com.heima.model.order.pojos.ServiceAndOrder;
import com.heima.model.order.vos.PartOrderVo;
import com.heima.model.order.vos.ServiceOrderVo;
import com.heima.model.shop.dtos.ServiceAndOrderLogDto;
import com.heima.model.shop.pojos.Part;
import com.heima.model.user.pojos.Car;
import com.heima.model.user.pojos.Receiver;
import com.heima.order.mapper.OrderMapper;
import com.heima.order.mapper.PartAndOrderMapper;
import com.heima.order.mapper.ServiceAndOrderMapper;
import com.heima.order.service.OrderService;
import com.heima.utils.threadlocal.AdminThreadLocalUtils;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Version: V1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    UserFeign userFeign;

    @Autowired
    ShopFeign shopFeign;

    @Autowired
    PartAndOrderMapper partAndOrderMapper;

    @Autowired
    ServiceAndOrderMapper serviceAndOrderMapper;

    @Autowired
    OrderMapper orderMapper;

    /**
     * 查询服务订单业务中的 未处理订单列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findNotProcessPage(OrderDto dto) {
        if (dto == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        //1 检查分页参数
        dto.checkParam();
        //2 执行查询
        // 分页
        Page<Order> page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Order> wrapper = getOrderLambdaQueryWrapper(dto);

        //查询过期订单
        //小于一天前日期
        String date = DateTime.now().minusDays(1).toString("yyyy-MM-dd hh:mm:ss");
        wrapper.lt(Order::getCreateDate, date);
        //按订单创建时间倒序
        wrapper.orderByDesc(Order::getCreateDate);

        //执行过期订单查询
        IPage<Order> outDatePage = page(page, wrapper);

        //3 处理过期订单分页数据集合
        long ourDateTotal = outDatePage.getTotal();
        //分割页
        long size = ourDateTotal % dto.getSize() == 0 ? 0 : ourDateTotal % dto.getSize();
        long number = ourDateTotal / dto.getSize();
        List<Order> outDateRecords = outDatePage.getRecords();

        //未过期订单送条数
        LambdaQueryWrapper<Order> queryWrapper = getOrderLambdaQueryWrapper(dto);
        //按订单创建时间倒序
        queryWrapper.orderByDesc(Order::getCreateDate);

        //大于等于一天前日期
        queryWrapper.ge(Order::getCreateDate, date);
        Integer notDateCount = orderMapper.selectCount(queryWrapper);


        //4 当前页在过期订单分页内
        if (dto.getPage() <= number) {
            // 返回封装分页结果
            return new PageResponseResult(dto.getPage(), dto.getSize(),
                    (int) (ourDateTotal + notDateCount), outDateRecords);
        }

        //当前页在过期订单分页后,且刚好过期订单分页后 第一条不过期订单数据
        if (dto.getPage() == number + 1 && size == 0) {

            Page notOutPage = new Page(1, dto.getSize());
            //执行未过期订单查询
            IPage<Order> notDatePage = page(notOutPage, queryWrapper);

            // 返回封装分页结果
            return new PageResponseResult(dto.getPage(), dto.getSize(),
                    (int) (ourDateTotal + notDateCount), notDatePage.getRecords());
        }

        //交叉分水岭
        if (dto.getPage() == number + 1 && size > 0) {

            //执行过期订单查询 第一次已经查过
            List<Order> outDateList = outDatePage.getRecords();

            Page<Order> notPage = new Page<>(1, dto.getSize() - size);
            //执行未过期订单查询
            IPage<Order> notDatePage = page(notPage, queryWrapper);
            List<Order> notDateList = notDatePage.getRecords();


            //前size条过期服务订单，后dto.getSize()条未过期订单
            outDateList.addAll(notDateList);

            // 返回封装分页结果
            return new PageResponseResult(dto.getPage(), dto.getSize(),
                    (int) (ourDateTotal + notDateCount), outDateList);

        }

        if (dto.getPage() > number + 1) {
            Page<Order> notPage = new Page<>(dto.getPage() - (number + 1) + 1, dto.getSize());
            //执行未过期订单查询
            IPage<Order> notDatePage = page(notPage, queryWrapper);
            List<Order> notDateList = notDatePage.getRecords();

            // 返回封装分页结果
            return new PageResponseResult(dto.getPage(), dto.getSize(),
                    (int) (ourDateTotal + notDateCount), notDateList);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_BUSY, "系统正忙，请稍后重试");

    }

    private LambdaQueryWrapper<Order> getOrderLambdaQueryWrapper(OrderDto dto) {
        // 条件查询
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        // 订单编号
        if (StringUtils.isNotBlank(dto.getCode())) {
            wrapper.eq(Order::getCode, dto.getCode());
        }
        // 商家名称
        if (StringUtils.isNotBlank(dto.getStoreName())) {
            wrapper.eq(Order::getStoreName, dto.getStoreName());
        }

        // 客户名称
        if (StringUtils.isNotBlank(dto.getContact())) {
            wrapper.eq(Order::getContact, dto.getContact());
        }

        //创建时间
        if (dto.getBeginDate() != null && dto.getOverDate() != null) {
            wrapper.between(Order::getCreateDate, dto.getBeginDate(), dto.getOverDate());
        }

        //查询未处理服务订单
        wrapper.eq(Order::getOrderStatus, 0);
        //查询未关闭服务订单
        wrapper.eq(Order::getIsClose, 0);
        //查询服务订单
        wrapper.eq(Order::getType,1);

        return wrapper;
    }

    /**
     * 查询服务订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public ResponseResult findServiceOrderInfo(Long orderId) {
        if (orderId == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        //获得服务订单的数据
        Order order = getById(orderId);

        if (order == null || order.getId() == null || order.getCarId() == null
                || order.getStoreId() == null) {
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //远程调用主体服务 获得车主信息
        Car car = userFeign.findCar(order.getCarId());

        //获得服务id集合
        List<ServiceAndOrder> serviceAndOrders = serviceAndOrderMapper.selectList(Wrappers.<ServiceAndOrder>lambdaQuery()
                .eq(ServiceAndOrder::getOrderId, orderId).select(ServiceAndOrder::getServiceId));

        List<Long> serviceIds = serviceAndOrders.stream().map(serviceAndOrder -> {
            return serviceAndOrder.getServiceId();
        }).collect(Collectors.toList());

        ServiceAndOrderDto serviceAndOrderDto = new ServiceAndOrderDto();
        serviceAndOrderDto.setServiceIds(serviceIds);
        serviceAndOrderDto.setOrderId(orderId);
        serviceAndOrderDto.setStoreId(order.getStoreId());

        Map<String, Object> resultMap = shopFeign.findServiceAndStore(serviceAndOrderDto);

        if (car != null) {
            resultMap.put("car", car);
        }

        return ResponseResult.okResult(resultMap);
    }

    /**
     * 查询配件订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public ResponseResult findPartOrderInfo(Long orderId) {
        if (orderId == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询配件订单数据
        Order order = getById(orderId);

        if (order == null || order.getId() == null || order.getStoreId() == null) {
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //远程调用主体服务 获得收货方信息
        Receiver receiver = userFeign.findReceiver(orderId);

        List<PartAndOrder> partAndOrders = partAndOrderMapper.selectList(Wrappers.<PartAndOrder>lambdaQuery()
                .eq(PartAndOrder::getOrderId, orderId).select(PartAndOrder::getPartId));

        List<Long> partIds = partAndOrders.stream().map(partAndOrder -> {
            return partAndOrder.getPartId();
        }).collect(Collectors.toList());

        PartAndOrderDto partAndOrderDto = new PartAndOrderDto();
        partAndOrderDto.setStoreId(order.getStoreId());
        partAndOrderDto.setPartIds(partIds);
        partAndOrderDto.setOrderId(orderId);

        //远程调用商品服务 获得商家和配件集合信息
        Map<String, Object> resultMap = shopFeign.findPartsAndStore(partAndOrderDto);
        List<Part> parts = JSON.parseArray(JSON.toJSONString(resultMap.get("parts")), Part.class);

        //商品订单总价
        Integer totalPrice = 0;
        for (Part part : parts) {
            totalPrice += part.getPartMoney();
        }
        if (totalPrice != null) {
            resultMap.put("totalPrice", totalPrice);
        }

        //商品详细信息
        if (parts != null && parts.size() > 0) {
            resultMap.put("parts", parts);
        }
        //收货方
        if (receiver != null) {
            resultMap.put("receiver", receiver);
        }
        return ResponseResult.okResult(resultMap);
    }


    /**
     * 关闭未处理过期服务订单
     *
     * @param orderId
     * @return
     */
    @Override
    @GlobalTransactional
    public ResponseResult closeServiceOrder(Long orderId) {
        if (orderId == null) {
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        Order order = getById(orderId);
        if (order == null || order.getId() == null) {
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        if (order.getIsClose() != null && order.getIsClose() == 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_ALREADY_EXIST, "该服务订单已经关闭，无需再次关闭");
        }
        if (order.getIsClose() != null && order.getIsClose() == 0) {
            order.setIsClose(1);
            updateById(order);

            //远程保存操作员操作记录
             AdminThreadLocalUtils.getUser();
//            if (user==null||user.getId()==null){
//                CustException.cust(AppHttpCodeEnum.NO_OPERATOR_AUTH);
//            }
            ServiceAndOrderLogDto logDto = new ServiceAndOrderLogDto();
            logDto.setDescription("关闭未处理过期服务订单");
            logDto.setHandleTime(new Date());
            logDto.setOrderId(orderId);
            logDto.setOrderStatus(order.getOrderStatus().toString());
//            logDto.setUserId(user.getId().longValue());
            logDto.setUserId((long) 1);

            //远程调用用户模块 获得当前操作员的名字
//            ResponseResult result = userFeign.getUserRealname(user.getId().longValue());
            ResponseResult result = userFeign.getUserRealname((long) 1);

            logDto.setUserRealname((String) result.getData());

            ResponseResult responseResult = shopFeign.insertServiceLong(logDto);
            if (responseResult.getCode()==0){
                return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
            }
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_BUSY);
    }

    /**
     * 查询订单列表
     * 1服务  2配件
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findPartOrderPage(OrderDto dto) {
        //1 参数检查
        if (dto == null || dto.getType() == null || (dto.getType() < 1 || dto.getType() > 2)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 分页检查
        dto.checkParam();

        //2 执行查询
        // 分页
        Page<Order> page = new Page(dto.getPage(), dto.getSize());
        // 条件查询
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();

        // 订单编号
        if (StringUtils.isNotBlank(dto.getCode())) {
            wrapper.eq(Order::getCode, dto.getCode());
        }
        // 商家名称
        if (StringUtils.isNotBlank(dto.getStoreName())) {
            wrapper.eq(Order::getStoreName, dto.getStoreName());
        }

        // 客户名称
        if (StringUtils.isNotBlank(dto.getContact())) {
            wrapper.eq(Order::getContact, dto.getContact());
        }

        //创建时间
        if (dto.getBeginDate() != null && dto.getOverDate() != null) {
            wrapper.between(Order::getCreateDate, dto.getBeginDate(), dto.getOverDate());
        }

        // 订单状态
        if (dto.getOrderStatus() != null) {
            wrapper.eq(Order::getOrderStatus, dto.getOrderStatus());
        }

        //如果为服务订单 有支付状态查询
        if (dto.getType() != null && dto.getType() == 1) {
            if (dto.getPayStatus() != null) {
                wrapper.eq(Order::getPayStatus, dto.getPayStatus());
            }
            //不查询已关闭服务订单
            wrapper.ne(Order::getIsClose, 1);
            //不查询未处理服务订单
            wrapper.ne(Order::getOrderStatus, 0);
        }

        IPage<Order> pageResult = page(page, wrapper);
        //分页数据集合
        List<Order> records = pageResult.getRecords();

        //配件订单列表查询时
        if (dto.getType() == 2) {
            records = records.stream().map(order -> {
                PartOrderVo partOrderVo = new PartOrderVo();
                partOrderVo.setCode(order.getCode());
                partOrderVo.setCreateDate(order.getCreateDate());
                partOrderVo.setStoreName(order.getStoreName());
                partOrderVo.setStoreTel(order.getStoreTel());
                partOrderVo.setContact(order.getContact());
                partOrderVo.setContactTel(order.getContactTel());
                partOrderVo.setOrderStatus(order.getOrderStatus());
                partOrderVo.setMoney(order.getMoney());
                return partOrderVo;
            }).collect(Collectors.toList());

        } else {
            //服务订单列表查询时
            records = records.stream().map(order -> {
                ServiceOrderVo serviceOrderVo = new ServiceOrderVo();
                serviceOrderVo.setCode(order.getCode());
                serviceOrderVo.setCreateDate(order.getCreateDate());
                serviceOrderVo.setStoreName(order.getStoreName());
                serviceOrderVo.setStoreTel(order.getStoreTel());
                serviceOrderVo.setContact(order.getContact());
                serviceOrderVo.setContactTel(order.getContactTel());
                serviceOrderVo.setOrderStatus(order.getOrderStatus());
                serviceOrderVo.setMoney(order.getMoney());

                serviceOrderVo.setPayStatus(order.getPayStatus());
                return serviceOrderVo;
            }).collect(Collectors.toList());

        }
        //3 封装分页结果
        return new PageResponseResult(dto.getPage(), dto.getSize(),
                (int) pageResult.getTotal(), records);
    }
}
