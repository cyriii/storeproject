package com.cyriii.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser getByUserName(String userName);

    String getIdByUserName(String userName);
}
