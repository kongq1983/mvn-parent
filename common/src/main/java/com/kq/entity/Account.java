package com.kq.entity;

import lombok.Data;

import java.util.Date;

/**
 * Account
 *
 * @author kq
 * @date 2019-10-11
 */
@Data
public class Account {

    private Long id;
    private String username;
    private String phone;
    private String province;
    private Date createTime;


    @Override
    public String toString() {
        return "My Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
