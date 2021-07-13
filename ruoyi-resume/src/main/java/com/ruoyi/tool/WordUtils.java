package com.ruoyi.tool;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;


import freemarker.template.Configuration;
import freemarker.template.Template;


import java.io.*;
import java.util.Map;
public class WordUtils {

	/**
	 * 下载动态生成的Word文件。只支持ftl文件模板。
	 * @param map
	 * @param templatePath 模板路径，即模板放在哪个位置
	 * @param templateName 模板名称。
	 * @param downloadName 导出文件名。
	 * @throws IOException
	 */
    public static String  downloadWordByTemplate( Map map, String templatePath, String templateName, String downloadName) throws IOException {
	   //导出文件路径
		String enclosurepath =RuoYiConfig.getEnclosurepath();
    	MapNullToSpace(map);
    	Configuration configuration = new Configuration();
    	configuration.setDefaultEncoding("utf-8");
    	configuration.setDirectoryForTemplateLoading(new File(templatePath));
    	Template freemarkerTemplate = configuration.getTemplate(templateName);


		String filePath =createDoc(map,freemarkerTemplate,downloadName,enclosurepath);
		int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
		String currentDir = StringUtils.substring(filePath, dirLastIndex);
		String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir;


		return pathFileName;

	}

	private static String createDoc(Map<?, ?> dataMap, Template template,String downloadName,String enclosurepath) throws IOException {
		String name =  enclosurepath+"/"+DateUtils.datePath()+"/"+downloadName;
		File f = new File(name);
		if (!f.getParentFile().exists())
		{
			f.getParentFile().mkdirs();
		}
		if (!f.exists())
		{
			f.createNewFile();
		}
        Template t = template;
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);

            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return name;
    }



	/**
	 * Map中null值改为空字符串
	 * @param map
	 */
	private static void MapNullToSpace(Map<String, Object> map) {
		for (String key : map.keySet()) {
			if (null == map.get(key)) {
				map.put(key, "");
			}
    	}
	}



}
