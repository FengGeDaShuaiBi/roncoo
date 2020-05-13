package com.course.service;

import com.model.dao.UserMapper;
import com.model.generator.mapper.AdvMapper;
import com.model.generator.pojo.Adv;
import com.model.generator.pojo.AdvExample;
import com.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("当前账号不存在");
        user.setRoles(userMapper.getRoleByUserId(user.getId()));
        return user;
    }

    @Autowired
    AdvMapper advMapper;

    public int insert(Adv adv) {
        return advMapper.insert(adv);
    }

    public List<Adv> se(AdvExample example) {
        return advMapper.selectByExample(example);
    }

}
