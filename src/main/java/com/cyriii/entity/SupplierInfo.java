package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 供应商
 */
@Data
@Accessors(chain = true)
public class SupplierInfo {


    private String id;

    /**
     * 供应商名称
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
