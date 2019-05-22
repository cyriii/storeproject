package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.SysUserDao;
import com.cyriii.entity.SysUser;
import com.cyriii.service.SysUserService;
import com.cyriii.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser getByUserName(String userName){
        return sysUserDao.selectByUserName(userName);
    }

    @Override
    public String getIdByUserName(String userName) {
        return sysUserDao.selectIdByUserName(userName);
    }

    @Override
    public boolean save(SysUser entity) {
        // 查询用户名是否存在
        SysUser sysUser = getByUserName(entity.getUserName());
        if(sysUser != null){
            return false;   // 抛出异常
        }
        entity.setId(UUIDUtils.getUUID());
        // 对密码进行加密
        entity.setPwd(passwordEncoder.encode(entity.getPwd()));
        return super.save(entity);
    }
}
