package com.cyriii.controller;

import com.cyriii.common.ResultMessage;
import com.cyriii.entity.SysUser;
import com.cyriii.entity.SysUserVO;
import com.cyriii.exception.StoreException;
import com.cyriii.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户信息
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/user")
    public ResultMessage getCurrentUser(){
        ResultMessage resultMessage = new ResultMessage();
        SysUserVO sysUserVO = sysUserService.getCurrentUser();
        resultMessage.setData(sysUserVO);
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
        try {
            sysUserService.saveSysUser(sysUser);
        } catch (StoreException e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

    @PutMapping("/user")
    public ResultMessage update(@RequestBody SysUserVO sysUserVO){
        ResultMessage resultMessage = new ResultMessage();
        sysUserService.updateById(sysUserVO);
        return resultMessage;
    }

    @PostMapping("/user/changepwd")
    public ResultMessage changePwd(@RequestBody Map<String, Object> params){
        ResultMessage resultMessage = new ResultMessage();
        try {
            sysUserService.changePwd(params);
        } catch (Exception e) {
            resultMessage.setCode(ResultMessage.ERROR_CODE).setMessage(e.getMessage());
        }
        return resultMessage;
    }

}
