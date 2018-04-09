package com.yoyoig.wechatUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class WechatUtil {

    private static final String APP_ID = "wxc2534e9ca299601d";

    private static final String APP_SECRET = "c3489bf8d1325ec8b271b243bb09198b";

    private static final String GRANT_TYPE = "authorization_code";

    /**
     * 通过code获取access_token
     * @param code
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static JSONObject getAccessToken(String code) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPath("/sns/oauth2/access_token")
                .addParameter("appid", APP_ID)
                .addParameter("secret",APP_SECRET)
                .addParameter("code",code)
                .addParameter("grant_type",GRANT_TYPE);
        byte[] content = Request.Get(uriBuilder.build()).connectTimeout(10000)
                .execute().returnContent().asBytes();
        return JSON.parseObject(new String(content, Charset.defaultCharset()));
    }

    /**
     * 通过access_token和open_id获取用户基本信息
     * @param accessToken
     * @param openId
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static JSONObject getUserInfo(String accessToken,String openId) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost("api.weixin.qq.com")
                .setPath("/sns/userinfo")
                .addParameter("access_token", accessToken)
                .addParameter("openid", openId);
        // GET
        byte[] content = Request.Get(uriBuilder.build()).connectTimeout(10000)
                .execute().returnContent().asBytes();
        return JSON.parseObject(new String(content, Charset.defaultCharset()));
    }
}
