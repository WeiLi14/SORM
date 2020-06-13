package com.sorm.utils;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.utils
 * @ClassName: StringUtils
 * @Author: HAND_WEILI
 * @Description: 字符串相关的操作
 * @Date: 2020/6/10 12:57
 * @Version: 1.0
 */
public class StringUtils {

    /**
     * 将首字母大写
     * @param str
     * @return
     */
    public static String fristChar2UpperCase(String str){
        // abcd-->Abcd
       return str.toUpperCase().substring(0,1)+str.substring(1);
    }
}
