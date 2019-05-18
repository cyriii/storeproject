package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.SysUserDao;
import com.cyriii.entity.SysUser;
import com.cyriii.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
}
