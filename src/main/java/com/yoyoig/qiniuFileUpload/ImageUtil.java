package com.yoyoig.qiniuFileUpload;


import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author mingke.yan@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2018/3/13
 */
public class ImageUtil {

    public static final String UPLOAD_AVATAR = "avatar";

    public static final String UPLOAD_LICENCE = "licence";

    /**
     * 头像最大大小
     */
    public static final long AVATAR_SIZE =  1024*1024;

    /**
     * 图片格式
     */
    public static final String IMAGE_REG = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";


    public static  void deleteImage(QiniuProperties qiniuProperties, String filepath){

        String filename = filepath.substring(filepath.lastIndexOf("/")+1);
        Mac mac = new Mac(qiniuProperties.getAccessKey(),qiniuProperties.getSecretKey());
        RSClient client = new RSClient(mac);
        // bucket有自己在七牛云上创建
        client.delete("images", filename);
    }


    /**
     * 上传文件到七牛云
     * @param filename
     * @param file
     * @throws AuthException
     */
    public static void upImage(QiniuProperties qiniuProperties, String filename, MultipartFile file, HttpServletRequest request) throws AuthException, IOException, JSONException {

        PutExtra extra = new PutExtra();

        //获取项目路径
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        File dir = new File(filePath);
        if(!dir.exists()) {
            dir.mkdir();
        }

        //生成临时图片路径
        String path = filePath + file.getOriginalFilename();
        File tempFile =  new File(path);

        //生成临时文件
        FileUtils.copyInputStreamToFile(file.getInputStream(),tempFile);

        //上传本地图片
        IoApi.putFile(getUpToken(qiniuProperties), filename, tempFile, extra);

        //删除临时图片
        tempFile.delete();


    }

    /**
     * 获取上传token
     * @return
     * @throws AuthException
     */
    private static String getUpToken(QiniuProperties qiniuProperties) throws AuthException, JSONException {

        Mac mac = new Mac(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        // bucket有自己在七牛云上创建
        String bucketName = "images";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
        return uptoken;
    }


}