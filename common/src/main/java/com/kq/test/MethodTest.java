package com.kq.test;

import com.kq.dto.Employee;

import java.lang.reflect.Method;

public class MethodTest {

    public static void main(String[] args) throws Exception{
        Method method = Employee.class.getMethod("setAge",Integer.class);

        System.out.println(method);
        System.out.println(method.getDeclaringClass());

    }

}
