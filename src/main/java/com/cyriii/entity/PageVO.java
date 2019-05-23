package com.cyriii.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class PageVO {

    private Integer current = 1;

    private Integer size = 10;

    private Map<String, Object> params = new LinkedHashMap<>();

}
