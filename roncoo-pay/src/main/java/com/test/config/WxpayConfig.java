package com.test.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WxpayConfig {

	// 微信公众号配置
	public static final String APPID="wx171686d5566aae60";
	public static final String AppSecret="dd53881sfdh204aa2f0c6aghj655a4fd5s89e67a9";

	// 微信支付配置
	public static final String MCHID="1510700851";
	public static final String ApiSecret="dgh233454567567sdgfhj890123gf234456fghs78934556701234";
	public static final String MCHNAME="有限公司";

	// 内网穿透域名
	public static final String domain="http://aaaa.vaiwan.com/";

	// 组装获取wxCode的url
	public static String getWxCodeUrl(){
		try {
			String returnUrl = URLEncoder.encode(WxpayConfig.domain+"toPayPage","utf-8");
			String wxUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxpayConfig.APPID+"&redirect_uri="+returnUrl+"&response_type=code&scope=snsapi_base#wechat_redirect";
			return wxUrl;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 组装获取WxOpenId的url
	public static String getWxOpenIdUrl(String code){
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WxpayConfig.APPID+"&secret="+WxpayConfig.AppSecret+"&code="+code+"&grant_type=authorization_code";
		return url;
	}
}
