package com.mustang.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mustang.lib_common.utils.ToastUtils;


/**
 * Created by Mustang on 2018/8/15.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    public void setPresenter(T presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.showLongToast(msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

//    protected abstract void initInject();
    protected abstract void loadErrorData();
}