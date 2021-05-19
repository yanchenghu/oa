package com.ruoyi.tool;




import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


/**
 * @Description: 简历批量瞎子啊
 * @Author ych
 * @Date 2021/5/8
 */
public class ZipUtils {


    public static void main(String[] args) {
        String remoteFilePath="http://work.zhuyanhr.com/prod-api/profile/avatar/2021/05/07/%E3%80%90%E6%B5%8B%E8%AF%95_%E5%8C%97%E4%BA%AC%E3%80%91%E7%9B%9B%E5%9B%BD%E5%BC%BA%202%E5%B9%B4050613172726.pdf";
        String lo="C:/Users/admin/Desktop/";
        downloadFile(remoteFilePath,lo);
    }



    /**
     * 获取远程文件
     * @param remoteFilePath 远程文件路径
     * @param localFilePath 本地文件路径
     */
    public static void downloadFile(String remoteFilePath, String localFilePath)
    {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String fileName = "";
        String ramdom = System.currentTimeMillis() + ""
                + new Random().nextInt(100) + new Random().nextInt(100)
                + new Random().nextInt(100) ;
        fileName = ramdom + "_" + fileName;
        File f = new File(localFilePath+fileName);
        try
        {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }





}
