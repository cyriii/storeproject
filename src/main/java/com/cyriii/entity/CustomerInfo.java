package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 客户信息
 */
@Data
@Accessors(chain = true)
public class CustomerInfo {

    private String id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String telNumber;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;
}
