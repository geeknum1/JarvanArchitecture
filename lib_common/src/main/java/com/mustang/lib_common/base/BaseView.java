package com.mustang.lib_common.base;


/**
 * Created by Mustang on 2018/8/15.
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateLoading();

    void stateMain();




}
