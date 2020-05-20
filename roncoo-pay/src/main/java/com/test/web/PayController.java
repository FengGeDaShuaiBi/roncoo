package com.test.web;

import com.test.config.WxpayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: miaozm
 * @Date: 2020/2/10 15:59
 */
@Controller
public class PayController {


	// 跳转二维码页面
	@RequestMapping("/toQrcode")
	public String toQrcode(HttpServletRequest request,ModelMap modelMap){
		modelMap.put("url",request.getRequestURL().toString().replace("toQrcode","toPayPage"));
		return "qrcode";
	}


	// 跳转支付页面
	@RequestMapping("/toPayPage")
	public String toPay(HttpServletRequest request,HttpServletResponse response,String code,ModelMap modelMap) throws Exception {
		String userAgent = request.getHeader("user-agent");
		System.out.println(userAgent);
		if (userAgent != null && userAgent.contains("AlipayClient")) {
			System.out.println("来自支付宝");
		}else if (userAgent != null && userAgent.contains("MicroMessenger")) {
			System.out.println("来自微信");
			System.out.println("code:"+code);
			if(code==null){
				//获取code:
				response.sendRedirect(WxpayConfig.getWxCodeUrl());
			}else{
				modelMap.put("name",WxpayConfig.MCHNAME);
				modelMap.put("money","0.01");
				modelMap.put("code",code);
				modelMap.put("reUrl",WxpayConfig.domain);
				return "wxPay";
			}

		}else{
			System.out.println("未知来源");
		}

		return "aliPay";
	}



}
