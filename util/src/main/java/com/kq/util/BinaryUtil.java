package com.kq.util;

/**
 * BinaryUtil
 *
 * @author kq
 * @date 2019-09-04
 */
public class BinaryUtil {

    public static final long LONG_BIT = 64L;

    public static String toBinaryString(long num) {
        String jdkBinaryString = Long.toBinaryString(num);

        long diff = LONG_BIT - jdkBinaryString.length();

        if(diff==0) {
            return jdkBinaryString;
        }


        StringBuilder str = new StringBuilder();

        for(int i=0;i<diff;i++) {
            str.append("0");
        }
        str.append(jdkBinaryString);

        return str.toString();
    }


}
