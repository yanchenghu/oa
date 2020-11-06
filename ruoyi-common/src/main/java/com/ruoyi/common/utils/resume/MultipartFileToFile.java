package com.ruoyi.common.utils.resume;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class MultipartFileToFile {
    public static final String tempFilePath = "D:\\linshi\\resume";//解析简历word


    //导入word解析
    public static File multipartFileToFile(MultipartFile dataFile) throws Exception {
        if (!dataFile.isEmpty()) {
            File tempDir = new File( tempFilePath );
            String filename = dataFile.getOriginalFilename();
            String[] fileIngredent = filename.split("\\.");
            File f = File.createTempFile(fileIngredent[0], "."+fileIngredent[1],tempDir);
            // 将mutipartFile转换成file
            dataFile.transferTo(f);
            // 删除临时文件
      /*      if(file.exists()){
                file.delete();
            }*/
            return f;
        }
        return null;
    }
}
