package com.test.config;
public class AlipayConfig {

	// 支付宝支付配置
	// 商户appid
	public static String APPID = "2016101800715881";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYFvKpg1lNrbKpi6XtZjklSxQ8ZZHQsTzcCKT/LhfC566znSmYcpqnszEBOkLin7xkUAwBGKn38ueOI/xGZFjRRHCuwaYFSzycvPPB9qdQPbMvCzUhk8vyOfkNFqnJpH5ghUr1S1MLteR90HAVzU6Wi/BSuIsmTLPSWtmf7jfJ/pLFF/9KoxKcZheWeWdvYn7j/pL1KCRdYtCKfyMKgD8U0oMcYtltmVDpwq2QYH5CIgaugjrP62hQIIPCSCc9ot46wvmw4VL9pYq4b+A6XXZehgt7yT51rjLuGdAX74pna+vr4iKFHj67hccGvz3+eD9og7iR419oS83NKZKFaKSrAgMBAAECggEAL925DaSrLIloW3f6BtdPUO9qNP/6fmXps45871sAW3UzRJBIERSwm+sHl01Q06ZWUnPm80lh5Qlr2w50TWszkNkrZbTInY3qS6IkTX1ldxmUFVA/rounelrIiVgCo9XDeHk6+Cx2vI7IlNhPDm2JOFRelIpEMQ+e4Kt7JpyArm+WYsr57ZjYZ5jpzeNGLIw4Ry7Xv+cvLDT30d2cW84hhcMhwfxI7cjXQoGVMMHOA9BsomTpJwERW65pdd9+FQyhB0WpEJwrc4e+7BwkFG4GGlWH0puTeElflyDpEZIWsMjh47lbOISZale/tzXl21rz7ZLUeJSZXeYqCRTe+fMrMQKBgQDJbOloHeWrg4jvOXM1kt6k3LywnkOHl0KaSrpQqtmAFobbNDbO7VcIM46TQgjxsi7ok9IQLR3RvwnqXnYZzHz9n+ucP8NQtg29mjV26ZVBxaWKSItTpajuW/JtjI19SwmWrSP269pBXF7BMRsAWsu+ypf9CJH9OW8sgSg86trbFQKBgQDBTA0rdeI9dqEE1Of1pJV3PXT7swZIrlf20N3XMlGDGL+vSlJhmqxqFP4EYhBfMMXPWvxHlKa19WaqBJAMcT0JSoFfsokR5aa22oXFk2GH38BVA3Ct7GICHQhUMNbKbOP5sLChSUm5hMULp/J4QX0Za8TVlUTypZ7+yjG9FK5wvwKBgHMK8HiV8tI2CyTPBD1KCD3TETYy/lDYFKM5IRbnrdgZhaZ3OQKQzg+SGtGCesdIxL/zhZvVmN1uJkSk9QEhKRZWm2QfivdzM3/iLr0fBi+NqlHDw0fgKw8QlvL4vusby3syooLqDDsefcQLvudB9HKhFZPgrDkb76JUll3MqXlNAoGAB61onIBLIxvSG2oFaIlH8nt7aP/cUQizr7buCL0T5bwW6PRvvzawgGojCLOIaO97+9dmS8WItZNwp3IU8pmQzgyqhM2HijFv5LVbONS4u/fUIMxJLm9EGUdj7EwgS9v1VhaBfXZ2uiqe1O6vv70CJf9UyxLpsSWI4h/Px+3JW+sCgYEAl5gRfYhIjq92WZdcdZX0fWXKPdk2S9TkBIo5nR3Hd+Ji4ZkyfKwI6XDOJOUoZY6OYyysvx9SXrFmYe6DbAiKiwMDplGEYfQyDLgfBLmanQOliqcVkSSLF1rNnlwli40EGtHs4cdZ5XYKHZU4c4CsYmhInKPxa7au7XBYvLyBnzM=";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://10.124.26.119:8899/toSuccess";
	public static String notify_url = WxpayConfig.domain+"aliNotify";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	//public static String return_url = "http://10.124.26.119:8899/toSuccess2";
	public static String return_url = WxpayConfig.domain+"toSuccess2";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmlhWlBhcGs4wUPp5yB3h1KO6z0odMobyNQwUQ+li4GEN22lby2xYLkXphC2up/HhMI+lnsE5+B68eznSmuubBDvAhlA+lXTI5BIWAljbKOgRhXRHjGwOe5qNTvcUD/jkt2E1K4quPYF/5QRX1PnTwAH0Vg6pxMz0udGjHp3zHcxpfbDSDk+NIS84vM0ryVmUJdQOQr+qIS8mOB8ZDMe9kMDjqiZ8Knu5mxLDmtjZn0LvIcrh13uiVtN87sxwTG0hJFH5KQWRCZQHXDrvnKDhoInp5Rex/Tdtz28C+cPtfE2ptKHzjaVQzC7EfwZ6mdsXNwB/g7ceem04OHqLcwdUPQIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";




}
