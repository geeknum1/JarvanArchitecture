package com.mustang.lib_common.base;

/**
 * Created by Mustang on 2018/8/15.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView, E extends BaseDataManager> {

    void attachView(T view);

    void attachDataManager(E dataManager);

    void detachView();




}
