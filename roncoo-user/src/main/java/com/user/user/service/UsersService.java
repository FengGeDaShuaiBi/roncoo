package com.user.user.service;

import com.model.dao.User1Mapper;
import com.model.generator.mapper.OrderInfoMapper;
import com.model.generator.mapper.UserMapper;
import com.model.generator.pojo.OrderInfo;
import com.model.generator.pojo.OrderInfoExample;
import com.model.generator.pojo.User;
import com.model.generator.pojo.UserExample;
import com.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UsersService {

    @Autowired
    User1Mapper user1Mapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    MailService mailService;

    @Autowired
    SmsService smsService;

    @Autowired
    RedisAPI redisAPI;

    public int updateUserPassword(long id, String mobile_psw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return user1Mapper.updateUserPassword(id, encoder.encode(mobile_psw));
    }

    public List<OrderInfo> selectUserOrder(OrderInfoExample example) {
        return orderInfoMapper.selectByExample(example);
    }

    public int insertUserRegister(User user) throws Exception {
        user.setMobilePsw(MD5.getMd5(user.getMobilePsw(), 12));
        int code = MD5.getRandomCode();
        smsService.send(user.getMobile(), "1", new String[]{String.valueOf(code), "1"});
        redisAPI.set(user.getMobile().toString(), String.valueOf(code).toString(), (long) 200, TimeUnit.SECONDS);
        return userMapper.insert(user);
    }

    public int updateUserStatus(User user, UserExample example) {
        return userMapper.updateByExampleSelective(user, example);
    }

    public List<User> loadUserByUserMobile(UserExample example) {
        return userMapper.selectByExample(example);
    }

}
