package com.spring.security.service;

import com.spring.security.entity.SysMenu;
import com.spring.security.entity.SysUser;
import com.spring.security.mapper.SysMenuMapper;
import com.spring.security.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author xuyang
 * @version 1.0
 * @date 2019/6/19 13:14
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.findByName(username);
        if (sysUser != null){
            List<SysMenu> menus = sysMenuMapper.findByUserId(sysUser.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysMenu sysMenu : menus){
                if (sysMenu != null && sysMenu.getName() != null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysMenu.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
        return new User(sysUser.getName(),sysUser.getPassword(), grantedAuthorities);
        }else {
            throw new UsernameNotFoundException(sysUser.getName() + " not found");
        }
    }
}