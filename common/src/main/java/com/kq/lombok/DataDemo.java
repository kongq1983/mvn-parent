package com.kq.lombok;

import lombok.Data;

/**
 * DataDemo
 *
 * @author kq
 * @date 2019-09-03
 */
@Data
public class DataDemo {

    private String address;

    private Integer age;

    /**
     * 不会被lombok覆盖
     * @return
     */
    public String getAddress(){
        return "address";
    }

}
