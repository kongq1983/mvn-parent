package com.kq.mvn.order.controller;

import com.kq.dto.DtoResult;
import com.kq.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kq.util.DateUtil;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kq
 * @date 2020-08-17 14:17
 * @since 2020-0630
 */

@RestController
@Slf4j
public class OrderController {

    private AtomicLong ato = new AtomicLong(0);

    @RequestMapping("/order/view/{id}")
    public DtoResult view(@PathVariable("id") String id){

        log.info("查看订单ID :{}", id);

        Order order = new Order();
        order.setId(Long.valueOf(id));
        order.setCreateTime(new Date());
        order.setOrderNo(DateUtil.getFormatFull()+"-"+ato.incrementAndGet());

        log.info("订单加载 id={}, data={}",id,order);

        DtoResult result = new DtoResult();
        result.setCode("18800000");
        result.setResult(order);

        return result;

    }

}
