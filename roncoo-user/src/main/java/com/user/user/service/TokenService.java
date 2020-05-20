package com.user.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.model.generator.pojo.User;
import com.utils.MD5;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private RedisAPI redisAPI;

    public String gerenateToken(String userAgent, User user) throws Exception {
        //生成Token
        StringBuilder str = new StringBuilder();
        str.append("token:");//添加前缀分类

        UserAgent agent = UserAgent.parseUserAgentString(userAgent);
        if (agent.getOperatingSystem().isMobileDevice())
            str.append("MOBILE-");
        else
            str.append("PC-");
        str.append(MD5.getMd5(user.getMobile(), 32) + "-");
        str.append(user.getId() + "-");
        str.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        str.append(MD5.getMd5(userAgent, 6));

        return str.toString();
    }

    public void save(String token, User user) throws Exception {
        if (token.startsWith("token:PC-")) {
            redisAPI.set(token, String.valueOf(user), (long) 2000, TimeUnit.SECONDS);
        } else {
            redisAPI.set(token, (String) JSON.toJSON(user));
        }
    }

    public boolean validate(String userAgent, String token) throws Exception {

        if (!redisAPI.exists(token))
            return false;

        String userAgentMd5 = token.split("-")[4];//取到在token中的useragent

        if (!MD5.getMd5(userAgent, 6).equals(userAgentMd5))
            return false;

        return true;
    }

    public void delToken(String token) throws Exception {
        redisAPI.remove(token);
    }

    private long protectedTime = 15 * 60 * 1000;
    private long delay = 2 * 60;

    public String reloadToken(String userAgent, String token) throws Exception {
        if (!redisAPI.exists(token))
            throw new Exception("token无效");
        Date genTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(token.split("-")[3]);
        long passed = Calendar.getInstance().getTimeInMillis() - genTime.getTime();
        if (passed < protectedTime)
            throw new Exception("当前Token处于保护期内,无法置换,剩余时间：" + (protectedTime - passed) / 1000 + "秒");
        String str = (String) redisAPI.get(token);
        User itripUser = JSON.parseObject(str, User.class);
        String newToken = this.gerenateToken(userAgent, itripUser);
        redisAPI.set(token, str, delay, TimeUnit.SECONDS);
        this.save(newToken, itripUser);
        return newToken;
    }
}
