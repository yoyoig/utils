package com.yoyoig.otherUtils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mingke.yan@hand-china.com
 * @version 1.0
 * @name
 * @description
 * @date 2018/3/7
 */
public class ImageUtil {

    /**
     * 文件保存路径
     */
    private static String path = File.separator+"usr"+File.separator+"share"+File.separator+"tomcat"+File.separator+"resource"+File.separator+"picture"+File.separator;

    public static void saveImage(MultipartFile image, Object object) throws IOException {
        if(image != null) {
            // 生成新文件名
            String filename = image.getOriginalFilename();
            String subName = filename.substring(filename.lastIndexOf("."));
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
            Date now = new Date();
            String newName = format.format(now).toString() + subName;

            // 将文件保存到本地地址
            String filePath = path+newName;
            File localFile = new File(filePath);
            FileUtils.copyInputStreamToFile(image.getInputStream(), localFile);

            // 将生成的图片地址放到dto对象中,然后保存到数据库
            /**
             * 例如
             * object.setUrl("http://xxxx/"+newName);
             */
        }
    }

    public static void deleteImage(MultipartFile image, String imageUrl){
        // 每次添加图片时,进行操作,如果没有上传文件,则不删除.
        if(imageUrl != null && image !=null){
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);
            String filePath = path + fileName;
            File file = new File(filePath);
            file.delete();
        }
    }



}