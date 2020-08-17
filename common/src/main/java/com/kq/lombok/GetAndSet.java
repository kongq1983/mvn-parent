package com.kq.lombok;

import lombok.Getter;
import lombok.Setter;

/**
 * GetAndSet
 *
 * @author kq
 * @date 2019-09-03
 */
public class GetAndSet {

    @Setter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String address;

    @Setter
    private Integer age;

    @Getter(lazy=true)
    private final String photo = "";

    /**
     * 不会被lombok覆盖
     * @return
     */
    public String getAddress(){
        return "address";
    }

    /**
     * 不会被lombok覆盖
     * @return
     */
    public void setAge(Integer age){
        System.out.println("age="+age);
    }
}
