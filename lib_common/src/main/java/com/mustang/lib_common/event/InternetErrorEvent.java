package com.mustang.lib_common.event;

/**
 * Created by Mustang on 2018/8/20.
 */

public class InternetErrorEvent {
    private String msg;
    public InternetErrorEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
