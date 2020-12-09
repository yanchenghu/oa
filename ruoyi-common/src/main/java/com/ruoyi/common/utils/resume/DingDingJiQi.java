package com.ruoyi.common.utils.resume;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Value;

public class DingDingJiQi {


	private static String dingtoken="https://oapi.dingtalk.com/robot/send?access_token=154c35150438993842b55213945c20f512dcf32977758a81701955f08ab1363a";


	public static void DingDingd(String content) throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(dingtoken);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent(content);
		request.setText(text);
//		OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
//		at.setAtMobiles(Arrays.asList("18790317184"));
//      isAtAll类型如果不为Boolean，请升级至最新SDK
//		at.setIsAtAll(true);
//		request.setAt(at);


//		request.setMsgtype("markdown");
//		OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//		markdown.setTitle("杭州天气");
//		markdown.setText("#### 测试杭州天气 @18790317184\n" +
//		        "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//		        "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
//		        "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//		request.setMarkdown(markdown);
		OapiRobotSendResponse response = client.execute(request);
	}
	
	public static void main(String[] args) throws ApiException {
		DingDingd("我是机器人，测试消息勿回，，，喜报！！！恭喜，吴跃文成功入项一名人员到海隆科技");
	}
}
