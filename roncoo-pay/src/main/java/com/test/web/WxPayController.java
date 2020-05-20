package com.test.web;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.test.config.WxpayConfig;
import com.test.entity.AjaxResult;
import com.test.wxpay.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: miaozm
 * @Date: 2020/4/27 14:29
 */
@Controller
public class WxPayController {
    Logger log = LoggerFactory.getLogger(WxPayController.class);

    // 跳转支付成功页面
    @RequestMapping("/toWxSuccess")
    public String toWxSuccess(HttpServletRequest request, ModelMap modelMap) {
        return "wxPay/wxPaySuccess";
    }

    // 获取openid
    @RequestMapping("/getOpenId")
    @ResponseBody
    public AjaxResult getOpenId(HttpServletRequest request, String code, ModelMap modelMap) throws Exception {
        //获取access_token：
        String s = HttpUtil.get(WxpayConfig.getWxOpenIdUrl(code));
        System.out.println("获取access_token:" + s);
        JSONObject parse = (JSONObject) JSONObject.parse(s);
        String openId = (String) parse.get("openid");
        return AjaxResult.success(openId);
    }


    @GetMapping("/toWxPay")
    @ResponseBody
    public Map toWxPay(String body, String money, String orderNo, HttpSession session) throws Exception {
        WxRequestParam param = new WxRequestParam();
        Map<String, String> data = new HashMap<>();
        try {
            param.setBody(new String(body.getBytes(("ISO-8859-1")), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        param.setTotal_fee(money);
        param.setOut_trade_no(String.valueOf(System.currentTimeMillis()));
        session.setAttribute("orderNo", orderNo);
        session.setAttribute("tradeNo", param.getOut_trade_no());

        String codeUrl = WXPayUtil.getCodeUrl(param);
        data.put("codeUrl", codeUrl);
        return data;
        /*	System.out.println("openId"+openId);
         *//**
         * 统一下单所需数据
         *//*
		Map<String, String> reqData = new HashMap<>();
		reqData.put("out_trade_no", RandomUtil.randomNumbers(11));
		reqData.put("total_fee", "1");
		reqData.put("notify_url", WxpayConfig.domain+"wxNotify");
		reqData.put("body", WxpayConfig.MCHNAME);
		reqData.put("spbill_create_ip", CommonUtil.getIpAddress(request));
		reqData.put("openid", openId);
		reqData.put("trade_type", "JSAPI");
		*//**
         * 发送请求
         *//*
		WXPayConfigImpl config = new WXPayConfigImpl();
		WXPay pay = new WXPay(config,true,false);
		Map<String, String> map = pay.unifiedOrder(reqData);
		System.out.println(JSONObject.toJSONString(map));
		*//**
         * 判断http成功且结果成功
         *//*
		if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))){
			Map<String, String> data = new HashMap<>();
			//取出下面的值
			data.put("appId", map.get("appid"));
			data.put("timeStamp", Long.toString(WXPayUtil.getCurrentTimestamp()));
			data.put("package", "prepay_id="+map.get("prepay_id"));//取出里面的预订单id
			data.put("nonceStr", WXPayUtil.generateNonceStr());

			// -------重点--------如果是沙箱环境签名要设置为WXPayConstants.MD5

			data.put("signType", WXPayConstants.HMACSHA256);
			//重新加密并塞到data里
			data.put("paySign", WXPayUtil.generateSignature(data, config.getKey(),WXPayConstants.SignType.HMACSHA256));
			System.out.println(data.toString());
			return data;*/
    }


    /**
     * 微信回调方法
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/wxNotify")
    @ResponseBody
    public String notifyUrlByWx(HttpServletRequest request) throws Exception {
        System.out.println("1111111微信回调");
        Map<String, String> notifyMap = getRequestData(request); // 转换成map
        System.out.println(JSONObject.toJSONString(notifyMap));
        if (WXPayUtil.isSignatureValid(notifyMap, WxpayConfig.ApiSecret, WXPayConstants.SignType.HMACSHA256)) {
            // 签名正确
            // 进行处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
            String resultCode = notifyMap.get("result_code");
            if (resultCode.equals("SUCCESS")) {
                //处理业务逻辑

                //返回
                return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";


            } else {
                //如果支付失败
            }
        } else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
        }
        return "error";
    }

    // 转换request数据为map
    public Map getRequestData(HttpServletRequest request) {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();

            //解析xml成map
            return WXPayUtil.xmlToMap(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
