package com.cyriii.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyriii.entity.SysUser;
import com.cyriii.entity.SysUserVO;

import java.util.Map;

public interface SysUserService extends IService<SysUser> {

    SysUserVO getCurrentUser();

    SysUser getByUserName(String userName);

    boolean updateById(SysUserVO sysUserVO);

    boolean changePwd(Map<String, Object> params) throws Exception;
}
