package com.cyriii.utils;

import java.util.UUID;

public class UUIDUtils {

    /**
     * 获取无 - 的uuid
     * @return
     */
    public static String getUUID(){
        return getUUID(false);
    }

    /**
     * 获取uuid
     * @param hasLine   是否带 -
     * @return
     */
    public static String getUUID(Boolean hasLine){
        String uuid = UUID.randomUUID().toString();
        if(!hasLine){
            return uuid.replace("-", "");
        }
        return uuid;
    }

}
