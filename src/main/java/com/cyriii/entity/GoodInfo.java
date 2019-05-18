package com.cyriii.entity;

import lombok.Data;

/**
 * 商品信息
 */
@Data
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
}
