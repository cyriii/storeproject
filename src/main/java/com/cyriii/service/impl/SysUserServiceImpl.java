package com.cyriii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyriii.dao.SysUserDao;
import com.cyriii.entity.SysUser;
import com.cyriii.entity.SysUserVO;
import com.cyriii.exception.StoreException;
import com.cyriii.service.SysUserService;
import com.cyriii.utils.UUIDUtils;
import com.cyriii.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUserVO getCurrentUser() {
        SysUserVO sysUserVO = new SysUserVO();
        String id = userUtils.currentUserId();
        SysUser sysUser = super.getById(id);
        BeanUtils.copyProperties(sysUser, sysUserVO);
        sysUserVO.setUserId(id);
        return sysUserVO;
    }

    @Override
    public SysUser getByUserName(String userName){
        return sysUserDao.selectByUserName(userName);
    }

    @Override
    public boolean saveSysUser(SysUser entity) throws StoreException {
        // 查询用户名是否存在
        SysUser sysUser = getByUserName(entity.getUserName());
        if(sysUser != null){
            throw new StoreException("用户名已存在");
        }
        entity.setId(UUIDUtils.getUUID());
        // 对密码进行加密
        entity.setPwd(passwordEncoder.encode(entity.getPwd()));
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysUserVO sysUserVO) {
        SysUser entity = new SysUser();
        entity.setId(userUtils.currentUserId());
        BeanUtils.copyProperties(sysUserVO, entity);
        return super.updateById(entity);
    }

    @Override
    public boolean changePwd(Map<String, Object> params) throws Exception{
        SysUser sysUser = this.getById(userUtils.currentUserId());
        if(passwordEncoder.matches((String) params.get("oldPwd"), sysUser.getPwd())){
            sysUser.setPwd(passwordEncoder.encode((String) params.get("newPwd")));
        } else {
            throw new Exception("原密码有误");
        }
        return this.updateById(sysUser);
    }
}
