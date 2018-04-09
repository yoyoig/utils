package com.yoyoig.restTemplateUtils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 铭刻 on 2018/1/26.
 */
public class Object2MapUtil {

    /**
     * 通过反射将object转换成map，
     * 属性名：属性值
     * 为空则不放入
     * @param object
     * @return
     */
    public static Map<String,Object> ObjectForMap(Object object)  {

        Map<String,Object> map = new HashMap(16);
        if(object ==null){
            return map;
        }
        //获得类的所有属性
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field:fields) {
            String fieldName = field.getName();

            //设置该属性可访问
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /**
             * restTemplate底层转换时，不能将interger类型转换为string，导致错误
             * 应该是bug。
             */
            if(value !=null){
                value = value.toString();
                map.put(fieldName,value);
            }
        }
        return map;
    }

    /**
     * 通过反射将object转换成MultiValueMap，
     *
     * 属性名：属性值
     * 为空则不放入
     * @param object
     * @return
     */
    public static MultiValueMap<String,Object> ObjectForMutlMap(Object object)  {

        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();

        if(object ==null){
            return map;
        }
        //获得类的所有属性
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field:fields) {
            String fieldName = field.getName();

            //设置该属性可访问
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /**
             * restTemplate底层转换时，不能将interger类型转换为string，导致错误
             * 应该是bug。
             */
            if(value !=null){
                value = value.toString();
                map.add(fieldName,value);
            }
        }
        return map;
    }



}
