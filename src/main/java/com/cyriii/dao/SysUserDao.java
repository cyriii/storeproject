package com.cyriii.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyriii.entity.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    @Select("select id from sys_user where user_name = #{userName, jdbcType=VARCHAR}")
    String selectIdByUserName(String userName);

    @Select("select * from sys_user where user_name = #{userName, jdbcType=VARCHAR}")
    SysUser selectByUserName(String userName);

}
