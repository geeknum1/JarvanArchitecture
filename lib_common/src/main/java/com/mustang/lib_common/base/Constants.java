package com.mustang.lib_common.base;

/**
 * Created by Mustang on 2018/8/17.
 */

public class Constants {
    public static final boolean isOnline = false;
    public static final String SHOP_HOST = getShopHost();
    private static String getShopHost() {
        if (isOnline) {
            return "http://www.baidu.com";
        }
        return "http://www.baidu.com";
    }
    public static final String PERSON_HOST = getPersonHost();
    private static String getPersonHost() {
        if (isOnline) {
            return "http://gank.io/";
        }
        return "http://gank.io/";
    }

}
