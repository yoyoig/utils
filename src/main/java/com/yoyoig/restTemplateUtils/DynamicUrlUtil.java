package com.yoyoig.restTemplateUtils;

import java.util.Map;

/**
 * restTempatle
 * get方法需要在url带上参数
 * 该工具类动态生成URL
 * Created by 铭刻 on 2018/1/26.
 */
public class DynamicUrlUtil {

    /**
     * 根据条件动态返回URL
     * @param url  原始url
     * @param map 条件
     * @return
     */
    public static String getUrl(String url,Map<String,Object> map){
        StringBuilder stringBuilder = new StringBuilder();
        int i=0;
        stringBuilder.append(url);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            Object value = entry.getValue();
            if(value!=null){
                if(i == 0){
                    stringBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                }else{
                    stringBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                i = 1;
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

}