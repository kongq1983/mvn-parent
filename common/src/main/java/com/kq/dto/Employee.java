package com.kq.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 注解在类上；提供类所有属性的 getting 和 setting 方法，
 * 此外还提供了equals、canEqual、hashCode、toString 方法
 */
@Data
public class Employee implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String number;

}
