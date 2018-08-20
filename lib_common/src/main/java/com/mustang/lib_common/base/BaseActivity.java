package com.mustang.lib_common.base;

import android.support.v7.app.AppCompatDelegate;

import com.mustang.lib_common.utils.ToastUtils;


/**
 * Created by Mustang on 2018/8/15.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    protected T mPresenter;



    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
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
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
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

}