package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class StoreInfoVO {

    private String id;

    private String goodName;

    private String goodUnit;

    private BigDecimal storeNum;

}
