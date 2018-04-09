package com.yoyoig.restTemplateUtils;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author mingke.yan@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2018/4/9
 */
public class RestTemplateDemo {

    private RestTemplate restTemplate;

    private String url;

    private OAuth2AccessToken auth;

    public void getRequest(){
        Object condition = new Object();
        // 将数据对象转成map
        Map<String,Object> map = Object2MapUtil.ObjectForMap(condition);
        // 在map中条件get请求url后面的条件
        map.put("page",1);
        map.put("pageSize",10);
        map.put("access_token",auth.getValue());

        // 将条件拼装到url并发送get请求
        Object resutl = restTemplate.getForObject(DynamicUrlUtil.getUrl(url+"/query",map), Object.class, map);
    }

    public void postRequest(){
        Object condition = new Object();
        // 数据对象封装成x-www-form-urlencoded,发送post请求
        Object resutl = restTemplate.postForObject(url+"/create", HttpEntityUtil.getHttpEntityWithAuth(condition,auth),Object.class);
    }


}