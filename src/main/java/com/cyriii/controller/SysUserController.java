package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.SysUser;
import com.cyriii.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user/{userName}")
    public ResultMessage getByUserName(@PathVariable String userName){
        ResultMessage resultMessage = new ResultMessage();
        SysUser sysUser = sysUserService.getByUserName(userName);
        resultMessage.setData(sysUser);
        return resultMessage;
    }

    /**
     * 注册
     * @param sysUser
     * @return
     */
    @PostMapping("/register")
    public ResultMessage save(@RequestBody SysUser sysUser){
        ResultMessage resultMessage = new ResultMessage();
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
