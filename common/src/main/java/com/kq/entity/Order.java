package com.kq.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author kq
 * @date 2020-08-17 14:55
 * @since 2020-0630
 */

@Data
public class Order {

    private Long id;
    private String orderNo;
    private Date createTime;

    @Override
    public String toString() {
        return "My Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
