package com.mustang.module_news.presenter;

import com.mustang.lib_common.LoginBean;
import com.mustang.lib_common.base.RxPresenter;
import com.mustang.lib_common.widget.ProgressSubscriber;
import com.mustang.module_news.contract.LoginContract;
import com.mustang.module_news.data.NewsDataManager;


/**
 * Created by qht on 16/8/15.
 * Email:569877490@qq.com
 */

public class LoginPresenter extends RxPresenter<LoginContract.View, NewsDataManager> implements LoginContract.Presenter {

    public LoginPresenter() {
        attachDataManager(new NewsDataManager());
    }

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
    }

    @Override
    public void login( final String name, String pwd) {
        mDataManager.login(name, pwd)
                .subscribe(new ProgressSubscriber<LoginBean>(mCompositeDisposable) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        mView.jumpMain();
                    }


                });
    }

}
