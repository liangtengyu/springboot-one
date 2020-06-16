package com.lty.util;

import java.util.UUID;


public class UuidUtils {

    /**
     * 获取32位UUID
     * @return
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static void main(String[] args) {
        String uuid = get32UUID();
        System.out.println(uuid);
    }
}
