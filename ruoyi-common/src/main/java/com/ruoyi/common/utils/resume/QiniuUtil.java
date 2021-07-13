package com.ruoyi.common.utils.resume;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;


/**
 * @author ych
 * @Description
 * @create 2021-07-12 10:54
 */
public class QiniuUtil {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static final String ACCESS_KEY = "mAS93lUgIAmIP4EH9NpCYAo0XZkoWk28yz6NwzU7";
    private static final  String SECRET_KEY = "sDjnYcgrNR_enrUcjRP7d-BYnPrjw9_aC9Nhk59W";
    //要上传的空间
    private static final String BUCKET_NAME = "gymmp4";
    /**
     * 七牛云上传图片
     * @param file
     * @return
     */
    public static String uoloapQiniu(MultipartFile file, String fileName) throws Exception {
        String upload = FileUploadUtils.upload(file);
        String fsafsa=upload.replace("/profile","");
        File f =new File("D:/ruoyi/uploadPath"+fsafsa);
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.region2());//华南区注意重点
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "images/"+DateUtils.formatY_M_D2String(new Date(),DateUtils.FORMAT_Y_M_D_H_M_S)+fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET_NAME);
        String result = null;
        try {
            Response response = uploadManager.put(f.getPath(), key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            result = "mp4.zhuyanhr.com/"+putRet.key;
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }finally{
            if (f.getParentFile().exists()) {
                f.delete();
            }
        }
        return result;
    }












}
