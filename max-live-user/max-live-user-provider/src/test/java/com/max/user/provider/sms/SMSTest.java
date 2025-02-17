package com.max.user.provider.sms;


import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.cloopen.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * @author Max
 * @description
 * @date 2025/2/17 20:46
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SMSTest {

    @Test
    public static void main(String[] args) {

            //生产环境请求地址：app.cloopen.com
            String serverIp = "app.cloopen.com";
            //请求端口
            String serverPort = "8883";
            //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
            String accountSId = "2c94811c946f6bfb0194df8a10f70887";
            String accountToken = "04ca72e5f9244090932dff644fb2828e";
            //请使用管理控制台中已创建应用的APPID
            String appId = "2c94811c946f6bfb0194df8a12cf088e";
            CCPRestSmsSDK maxSms = new CCPRestSmsSDK();

            maxSms.init(serverIp, serverPort);
            maxSms.setAccount(accountSId, accountToken);
            maxSms.setAppId(appId);
            maxSms.setBodyType(BodyType.Type_JSON);
            String to = "13632871232";
            String templateId= "1";
            int code = new Random().nextInt(8999) + 1000;
            String[] datas = {String.valueOf(code)};
            String subAppend="1234";  //可选	扩展码，四位数字 0~9999
            String reqId="max";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
            HashMap<String, Object> result = maxSms.sendTemplateSMS(to,templateId,datas);
            //HashMap<String, Object> result = maxSms.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
            if("000000".equals(result.get("statusCode"))){
                //正常返回输出data包体信息（map）
                HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
                Set<String> keySet = data.keySet();
                for(String key:keySet){
                    Object object = data.get(key);
                    System.out.println(key +" = "+object);
                }
            }else{
                //异常返回输出错误码和错误信息
                System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            }
    }

}
