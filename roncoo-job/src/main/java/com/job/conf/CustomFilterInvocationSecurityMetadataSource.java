package com.job.conf;

import com.model.dao.MenuMapper;
import com.model.generator.pojo.SysMenu;
import com.model.generator.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPath = new AntPathMatcher();


    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String requestUrl = ((FilterInvocation) o).getRequestUrl();//提取出当前请求的URL

        List<SysMenu> allMenus = menuMapper.getMenuAll();//获取所有的menu
        for (SysMenu menu : allMenus
        ) {
            if (antPath.match(menu.getMenuUrl(), requestUrl)) {
                List<SysRole> roles = menu.getRoles();//获取资源所需要的角色
                String[] roleArr = new String[roles.size()];//定义一个长度为角色数量的数组
                for (int i = 0; i < roleArr.length; i++) {
                    roleArr[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
