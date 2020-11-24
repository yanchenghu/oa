package com.ruoyi.common.utils.resume;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAttendanceListRecordRequest;
import com.dingtalk.api.request.OapiAttendanceListscheduleRequest;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiAttendanceListRecordResponse;
import com.dingtalk.api.response.OapiAttendanceListscheduleResponse;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DingUtil {
    public static final Long agent_id = 262622998L;
    public static final String processCode = "PROC-DBDE589B-8CC6-44EA-B7A5-B3A90D47B4FB";//通知类审批编号
    public static final String appkey = "ding5urinhfhisf4kxdx";
    public static final String appsecret = "6vXcaU9xfohvTz9dMbM8KKCUfxSSoRD46fOW9KpvFdKlZXuxFhjcdJcdRngthLB1";
    public static final String TOKEN_URL = "https://oapi.dingtalk.com/gettoken?appkey=ding5urinhfhisf4kxdx&appsecret=6vXcaU9xfohvTz9dMbM8KKCUfxSSoRD46fOW9KpvFdKlZXuxFhjcdJcdRngthLB1";//获取token
    public static final String sendMessage_URL = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2";//发送消息
    public static final String sendApprove_URL = "https://oapi.dingtalk.com/topapi/processinstance/create";//发送审批
    public static final String receivedApprove_URL = "https://oapi.dingtalk.com/topapi/processinstance/get";//接收审批

    public static final String department_URL = "https://oapi.dingtalk.com/department/list?access_token=17ee691aa8ac3b77ae333a2a7251593e";//查询部门
    public static final String division_URL = "https://oapi.dingtalk.com/department/list_ids?access_token=4d71a70f6c033b72a2fa5c2cda9ebd9f&id=27041167";//查询子部门

    public static void main(String[] args) throws Exception {
        String url = "https://oapi.dingtalk.com/attendance/listRecord";
        String token = "ae6813bd0af13a01b929a559a2cdd8b8";
        details(url,token,"2019-11-20 00:00:00","2019-11-26 12:00:00");
    }

    //打卡详情
    public static JSONObject details(String url,String token,String date1,String date2) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiAttendanceListRecordRequest request = new OapiAttendanceListRecordRequest();
        request.setCheckDateFrom(date1);
        request.setCheckDateTo(date2);
        request.setUserIds(Arrays.asList("043561614424186967"));
        OapiAttendanceListRecordResponse execute = client.execute(request,token);
        JSONObject res = new JSONObject(execute.getBody());
        return res;
    }

    //考勤排版
    public static JSONObject scheduling(String url,String token,Date date) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiAttendanceListscheduleRequest request = new OapiAttendanceListscheduleRequest();
        request.setWorkDate(date);
        request.setOffset(0L);
        request.setSize(100L);
        OapiAttendanceListscheduleResponse execute = client.execute(request,token);
        JSONObject res = new JSONObject(execute.getBody());
        return res;
    }

    //收到审批
    public static JSONObject receivedApprove(String url,String processInstanceId,String token) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
        request.setProcessInstanceId(processInstanceId);
        OapiProcessinstanceGetResponse response = client.execute(request,token);
        JSONObject res = new JSONObject(response.getBody());
        //System.out.println(res.toString(4));
        return res;
    }

    //发送审批
    public static String sendApprove(String url,Long agenid,String processCode,String content,
                                     String Approvers,String originatorUserId,Long deptId,String token) throws Exception {
        DefaultDingTalkClient client = new DefaultDingTalkClient(url);
        OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
        request.setAgentId(agenid);
        request.setProcessCode(processCode);
        // 审批流表单参数，设置各表单项值
        List<OapiProcessinstanceCreateRequest.FormComponentValueVo> formComponentValues = new ArrayList<OapiProcessinstanceCreateRequest.FormComponentValueVo>();

        // 多行输入框
        OapiProcessinstanceCreateRequest.FormComponentValueVo vo2 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
        vo2.setName("内容");
        vo2.setValue(content);

        // 添加单行输入框、多行输入框、图片、明细到表单
        formComponentValues.add(vo2);

        request.setFormComponentValues(formComponentValues);
        request.setApprovers(Approvers);
        request.setOriginatorUserId(originatorUserId);
        request.setDeptId(deptId);
        OapiProcessinstanceCreateResponse response = client.execute(request,token);
        //System.out.println(response.getProcessInstanceId());
        return response.getProcessInstanceId();
    }

    //发送消息
    public static JSONObject sendMessage(String url,String content) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content-type", "application/json");
        // 设置内容信息
        JSONObject json = new JSONObject();
        JSONObject jsonCon = new JSONObject();
        JSONObject jsons = new JSONObject();
        json.put("msgtype", "text");
        jsonCon.put("content", content);
        json.put("text", jsonCon);
        jsons.put("msg", json);
        StringEntity params = new StringEntity(jsons.toString(), Consts.UTF_8);
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

    //获取token
    public static JSONObject getAccessToken(String url) throws Exception {
        HttpGet HttpGet = new HttpGet(url);
        HttpGet.addHeader("content-type", "application/json");
        // 发送请求
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(HttpGet);
        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        JSONObject res = new JSONObject(resCont);
        //System.out.println(res.toString(4));
        return res;
    }
}
