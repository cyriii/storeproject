package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.SysUser;
import com.cyriii.service.SysUserService;
import com.cyriii.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/{id}")
    public ResultMessage selectById(@PathVariable String id){
        ResultMessage resultMessage = new ResultMessage();
        SysUser sysUser = sysUserService.getById(id);
        resultMessage.setData(sysUser);
        return resultMessage;
    }

    @PostMapping("/user")
    public ResultMessage insert(@RequestBody SysUser sysUser){
        ResultMessage resultMessage = new ResultMessage();
        sysUser.setId(UUIDUtils.getUUID());
        sysUserService.save(sysUser);
        return resultMessage;
    }

    @PutMapping("/user")
    public ResultMessage update(@RequestBody SysUser sysUser){
        ResultMessage resultMessage = new ResultMessage();
        sysUserService.updateById(sysUser);
        return resultMessage;
    }

}
