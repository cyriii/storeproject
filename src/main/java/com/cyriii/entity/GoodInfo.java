package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商品信息
 */
@Data
@Accessors(chain = true)
public class GoodInfo {

    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品单位
     */
    private String unit;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品所属用户
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createDate;
}
