package com.sorm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: SORM
 * @Package: com.sorm.utils
 * @ClassName: ReflectUtils
 * @Author: HAND_WEILI
 * @Description: 操作反射相关的工具类
 * @Date: 2020/6/10 12:57
 * @Version: 1.0
 */
public class ReflectUtils {

    public static Object invokeGet(String fieldName,Object obj){
        Method m = null;
        Class clazz=obj.getClass();
        Object priKeyValue =null;
        try {
            m = clazz.getMethod("get"+ StringUtils.fristChar2UpperCase(fieldName),null);
            priKeyValue = m.invoke(obj,null);
            // 我们再次调用上个方法。

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return priKeyValue;

    }
}
