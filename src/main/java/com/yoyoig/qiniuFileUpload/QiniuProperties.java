package com.yoyoig.qiniuFileUpload;

import org.springframework.stereotype.Component;;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mingke.yan@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2018/3/13
 */
@Component
@ConfigurationProperties("qiniu.config")
@Data
public class QiniuProperties {

    private String accessKey;

    private String secretKey;

    private String url;

}