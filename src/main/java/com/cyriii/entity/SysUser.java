package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户信息
 */
@Data
@Accessors(chain = true)    // 开启链式调用
public class SysUser {

    /**
     * 用户id
     */
    private String Id;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 性别： 0女  1男
     */
    private Integer sex;

    /**
     * 联系电话
     */
    private String telNumber;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 头像地址
     */
    private String headUrl;

}
