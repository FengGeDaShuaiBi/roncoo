package com.test.wxpay;


import com.test.config.WxpayConfig;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: miaozm
 * @Date: 2020/4/27 09:41
 */

public class WXPayConfigImpl extends WXPayConfig {

	@Override
	public String getAppID() {

		return WxpayConfig.APPID;
	}

	@Override
	public String getMchID() {

		return WxpayConfig.MCHID;
	}

	@Override
	public String getKey() {

		return WxpayConfig.ApiSecret;
	}


	@Override
	public InputStream getCertStream() {
		//return null;
		return this.getClass().getResourceAsStream("apiclient_cert.p12");
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		IWXPayDomain iwxPayDomain = new IWXPayDomain() {
			@Override
			public void report(String domain, long elapsedTimeMillis, Exception ex) {

			}
			@Override
			public DomainInfo getDomain(WXPayConfig config) {
				return new DomainInfo(WXPayConstants.DOMAIN_API, true);
			}
		};
		return iwxPayDomain;
	}

	@Override
	public boolean shouldAutoReport() {

		return false;
	}



	public WXPayConfigImpl() {
		super();

	}

	/**
	 * 获取沙箱密钥
	 *
	 * @return
	 * @throws Exception
	 */
	public static String getsignkey() throws Exception {
		WXPayConfigImpl config = new WXPayConfigImpl();
		WXPay pay = new WXPay(new WXPayConfigImpl());
		int readTimeoutMs = 5000;
		int connectTimeoutMs = 5000;
		Map<String, String> reqData = new HashMap<>();
		reqData.put("mch_id", config.getMchID());
		reqData.put("nonce_str", WXPayUtil.generateNonceStr());
		String urlSuffix = "/sandboxnew/pay/getsignkey";
		String string = pay.requestWithoutCert(urlSuffix, pay.fillRequestData(reqData), connectTimeoutMs,
				readTimeoutMs);
		Map<String, String> map = WXPayUtil.xmlToMap(string);
		return map.get("sandbox_signkey");
	}

}

