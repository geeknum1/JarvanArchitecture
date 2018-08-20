package com.mustang.lib_common.base;

/**
 * * Created by Mustang on 2018/8/15.
 * <p>类说明</p>
 * 让各个模块都可以接收到application的生命周期
 * @name ApplicationDelegate
 */


public interface ApplicationDelegate {

    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);

}
