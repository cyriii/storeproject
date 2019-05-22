package com.cyriii.config;

import com.cyriii.entity.SysUser;
import com.cyriii.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证
 */
@Service
public class CustomUserDeatilService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUserName(userName);
        if(sysUser == null){
            throw new UsernameNotFoundException("not found");
        }
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        UserDetails userDetails = new User(sysUser.getUserName(),sysUser.getPwd(), grantedAuthorityList);
        return userDetails;
    }

}
