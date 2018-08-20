package com.mustang.module_news;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.mustang.lib_common.base.ARouterPath;
import com.mustang.lib_common.base.RootActivity;
import com.mustang.lib_common.utils.AllInputUtil;
import com.mustang.lib_common.utils.ToastUtils;
import com.mustang.module_news.contract.LoginContract;
import com.mustang.module_news.presenter.LoginPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@Route(path = ARouterPath.NewsAty)
public class NewsActivity extends RootActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {

    /**
     * 用户名：
     */
    private EditText mName;
    /**
     * 密码：
     */
    private EditText mPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;



    @Override
    protected int getLayout() {
        return R.layout.activity_news;
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void jumpMain() {
        ToastUtils.showLongToast("登录成功");
    }

    @Override
    public void showError(String msg) {
        ToastUtils.showLongToast(msg);
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setPresenter(new LoginPresenter());
        mName = (EditText) findViewById(R.id.name);
        mPwd = (EditText) findViewById(R.id.pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        stateLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stateMain();
            }
        },2000);
        mBtnLogin.setEnabled(false);
        //自动判断是否所有输入框都有输入内容，然后设置相应的button是否激活
        AllInputUtil.setListener(mBtnLogin, mName, mPwd);
        //像这种可能用户会连续点击的按钮设置防暴力点击
        addDisposable(RxView.clicks(mBtnLogin).subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                      mPresenter.login(mName.getText().toString().trim(),mPwd.getText().toString().trim());
                      /*  ARouter.getInstance()
                                .build(ARouterPath.GirlsAty)
                                .withString("params", "666")
                                .navigation(mContext, 3);*/
                    }
                }));

    }

    @Override
    protected void loadErrorData() {

    }




    @Override
    public void onClick(View view) {



    }
}
