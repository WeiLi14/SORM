package com.sorm.bean;

/**
 * 封装了JAVA属性和GET,SET方法得源代码
 *
 * @author HAND_WEILI 2020/06/10 21:19
 */
public class JavaFieldGetSet {
    /**
     * 属性得源码信息 如 private int userID
     */
    private String fieldInfo;
    /**
     * get方法得源信息
     */
    private String getInfo;
    /**
     * set方法得源码信息
     */
    private String setInfo;

    public JavaFieldGetSet() {
    }

    public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }

    @Override
    public String toString() {

      /*  System.out.println(fieldInfo);
        System.out.println(getInfo);
        System.out.println(setInfo);*/
        return super.toString();


    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }
}
