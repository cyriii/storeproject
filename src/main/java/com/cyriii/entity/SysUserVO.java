package com.cyriii.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysUserVO {
    private String userId;
    private String nickName;
    private Integer sex;
    private String telNumber;
    private String address;
    private String token;

}
