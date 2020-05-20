package com.user.user.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.OrderInfo;
import com.model.generator.pojo.OrderInfoExample;
import com.model.generator.pojo.User;
import com.model.generator.pojo.UserExample;
import com.model.vo.TokenVo;
import com.user.user.service.*;
import com.utils.EmptyUtils;
import com.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UsersService userService;

    @Autowired
    MailService mailService;

    @Autowired
    RedisAPI redisAPI;

    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "修改用户密码接口")
    @GetMapping("update/user/pass")
    public Dto updatePass(@RequestParam("id") long id, @RequestParam("password") String password) {
        int result = userService.updateUserPassword(id, password);
        if (result == 0)
            return DtoUtil.returnFail("修改失败", "10002");
        return DtoUtil.returnSuccess("修改成功");
    }

    @GetMapping("select/user/order")
    @ApiOperation(value = "获取单个用户订单信息接口", httpMethod = "GET")
    public Dto selectUserOrder(@RequestParam(value = "userId", required = true) long userId) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserNoEqualTo(userId);
        List<OrderInfo> infoList = userService.selectUserOrder(example);
        if (EmptyUtils.isEmpty(infoList))
            return DtoUtil.returnSuccess("未找到相应的订单");
        return DtoUtil.returnDataSuccess(infoList);
    }

    @PostMapping("insert/user/register")
    @ApiOperation(value = "新增前端用户测试接口")
    public Dto insertUserRegister(@RequestBody User user) {
        try {
            if (userService.insertUserRegister(user) == 0)
                return DtoUtil.returnFail("新增失败", "10002");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("新增成功");
    }

    @GetMapping("select/user/code")
    @ApiOperation(value = "手机用户激活接口")
    public Dto findCode(@RequestParam(value = "mobile", required = false) String mobile, @RequestParam(value = "code", required = false) String code) {
        String redisCode = (String) redisAPI.get(mobile);
        if (redisCode.equals(code)) {
            User user = new User();
            user.setStatusId((byte) 1);
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andMobileEqualTo(mobile);
            userService.updateUserStatus(user, example);
            return DtoUtil.returnSuccess("激活成功");
        }
        return DtoUtil.returnFail("激活失败", "10002");
    }

    @GetMapping("user/login")
    @ApiOperation(value = "用户手机登录", httpMethod = "GET")
    public Dto userLogin(@RequestParam(value = "mobile", required = true) String mobile, @RequestParam(value = "password", required = true) String password, HttpServletRequest request) {
        try {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andMobileEqualTo(mobile);
            List<User> users = userService.loadUserByUserMobile(example);
            if (EmptyUtils.isEmpty(users))
                return DtoUtil.returnFail("当前账户不存在", "10002");
            if (users.get(0).getMobilePsw().equals(MD5.getMd5(password, 12))) {
                String userAgent = request.getHeader("user-agent");
                String token = tokenService.gerenateToken(userAgent, users.get(0));
                tokenService.save(token, users.get(0));
                TokenVo vo = new TokenVo(token, Calendar.getInstance().getTimeInMillis() + 2000, Calendar.getInstance().getTimeInMillis());
                return DtoUtil.returnDataSuccess(vo);
            }
            return DtoUtil.returnFail("密码错误", "10003");
        } catch (Exception e) {
            return DtoUtil.returnFail("出现系统错误", "10002");
        }

    }
}
