package com.kq.lombok;

import lombok.SneakyThrows;

/**
 * SneakyThrowsDemo
 *
 * @author kq
 * @date 2019-09-03
 */

public class SneakyThrowsDemo {

    /**
     * 空方法不会生成try/catch
     */
    @SneakyThrows
    public void hello(){

    }

    @SneakyThrows
    public void hello1(){

    }

    @SneakyThrows
    public void parse(String number){
        Integer.parseInt(number);

    }

    @SneakyThrows
    public void parseMore(String number){
        Integer.parseInt(number);
        System.out.println("");
        Long.parseLong(number);
    }


//    public void parse(String number) {
//        try {
//            Integer.parseInt(number);
//        } catch (Throwable var3) {
//            throw var3;
//        }
//    }
//
//    public void parseMore(String number) {
//        try {
//            Integer.parseInt(number);
//            System.out.println("");
//            Long.parseLong(number);
//        } catch (Throwable var3) {
//            throw var3;
//        }
//    }


}
