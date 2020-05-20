package com.user.user.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.utils.SystemConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsService {
    public void send(String to, String templateId, String[] datas) throws Exception {
        //初始化SDK
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        //初始化服务器地址和端口
        sdk.init(SystemConfig.SERVICEIP, SystemConfig.SERVICEPORT);
        //初始化主帐号和主帐号令牌
        sdk.setAccount(SystemConfig.ACCOUNTSID, SystemConfig.AUTHTOKEN);
        //初始化应用ID
        sdk.setAppId(SystemConfig.APPID);

        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas);
        System.out.println("SDKTestGetSubAccounts result=" + result);

        if ("000000".equals(result.get("statusCode"))) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}
