package com.yoyoig.restTemplateUtils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.MultiValueMap;

/**
 * restTemplate.post
 * 需要的参数
 * Created by 铭刻 on 2018/1/26.
 */

public class HttpEntityUtil {

    /**
     * 传入条件对象，将该对象封装成HttpEntity作为条件传输
     * @param object
     * @return
     */
    public static HttpEntity getHttpEntity(Object object){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap multiValueMap = Object2MapUtil.ObjectForMutlMap(object);
        HttpEntity<Object> httpEntity = new HttpEntity(multiValueMap,headers);
        return httpEntity;
    }

    /**
     * 传入条件对象，将该对象封装成HttpEntity作为条件传输
     * 添加权限
     * @param object
     * @param auth
     * @return
     */
    public static HttpEntity getHttpEntityWithAuth(Object object, OAuth2AccessToken auth){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization","Bearer "+auth.getValue());
        MultiValueMap multiValueMap = Object2MapUtil.ObjectForMutlMap(object);
        HttpEntity<Object> httpEntity = new HttpEntity(multiValueMap,headers);
        return httpEntity;
    }

    /**
     * 传入一个multiValueMap,将该map封装成HttpEntity
     * 需要对象以外额外条件时使用
     * @param multiValueMap
     * @return
     */
    public static HttpEntity getHttpEntityByMap(MultiValueMap multiValueMap){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<Object> httpEntity = new HttpEntity(multiValueMap,headers);
        return httpEntity;
    }
}