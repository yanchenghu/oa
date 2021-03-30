package com.ruoyi.common.utils.resume;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class ResumeParserUtil {
    public static final String URL = "http://49.234.150.229:8000/parse_base/?handle_image=1";//解析简历word

    public static final String insertURL = "http://49.234.150.229:9000/resumes";//插入简历word

    public static final String portraitWordURL = "http://49.234.150.229:8000/analyze_base";//人物画像word

    public static final String insertJsonURL = "http://49.234.150.229:9000/resumes/json";//插入简历json

    public static final String portraitURL = "http://49.234.150.229:8000/analyze_json";//人物画像json

    public static final String matchingURL = "http://49.234.150.229:9000/match_resumes";//人岗匹配

    public static final String deleteURL = "http://49.234.150.229:9000/resumes/";//删除简历

    public static final String selResumeURL = "http://49.234.150.229:9000:port/resumes";//查询简历

    //导入word解析
    public static JSONObject resumeParser(String url, File fname) throws Exception {
        byte[] bytes = org.apache.commons.io.FileUtils.readFileToByteArray(fname);
        String data = new String(Base64.encodeBase64(bytes), Consts.UTF_8);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(data, Consts.UTF_8));

        // 设置头字段
        httpPost.addHeader("content-type", "application/json");

        // 设置内容信息
        JSONObject json = new JSONObject();
        json.put("file_name", fname.getName());   // 文件名
        json.put("resume_base", data); // 经base64编码过的文件内容
        StringEntity params = new StringEntity(json.toString());
        httpPost.setEntity(params);

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        //System.out.println(res.toString(4));
        return res;
    }

    //导入Json解析
    public static JSONObject portrait(String url,String content) throws Exception {
        if (content.startsWith("\uFEFF")){
            content = content.replace("\uFEFF", "");
        }
        JSONObject resumejson = new JSONObject(content);
        //System.out.println(content);

        HttpPost httpPost = new HttpPost(url);

        // 设置头字段
        httpPost.addHeader("content-type", "application/json");

        // 设置内容信息
        httpPost.setEntity(new StringEntity(resumejson.toString(), Consts.UTF_8));

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        //System.out.println(res.toString(4));
        return res;
    }

    //人岗匹配
    public static JSONObject recommender(String url,String job_title,String description) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("content-type", "application/json");

        // 设置内容信息
        JSONObject json = new JSONObject();
        json.put("job_title", job_title); // 替换为您的岗位名称
        json.put("description", description); //替换为您的岗位描述
        StringEntity params = new StringEntity(json.toString());
        httpPost.setEntity(params);

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpPost);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        //System.out.println(res.toString(4));
        return res;
    }

    //查询简历
    public static JSONObject selResume(String url) throws Exception {
        HttpGet HttpGet = new HttpGet(url);

        HttpGet.addHeader("content-type", "application/json");

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(HttpGet);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        System.out.println(res.toString(4));
        return res;
    }

    //删除简历
    public static String delete(String url) throws Exception {
        HttpDelete HttpDelete = new HttpDelete(url);

        HttpDelete.addHeader("content-type", "application/json");

        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(HttpDelete);

        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);

        JSONObject res = new JSONObject(resCont);
        //System.out.println(res.toString(4));
        return res.toString(4);
    }

}
