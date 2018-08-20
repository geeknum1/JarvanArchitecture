package com.mustang.module_news.presenter;

import com.mustang.lib_common.base.CommonSubscriber;
import com.mustang.lib_common.base.RxPresenter;
import com.mustang.module_news.bean.GirlsData;
import com.mustang.module_news.contract.NewsContract;
import com.mustang.module_news.data.NewsDataManager;



/**
 * Created by qht on 16/8/15.
 * Email:569877490@qq.com
 */

public class NewsPresenter extends RxPresenter<NewsContract.View, NewsDataManager> implements NewsContract.Presenter {

    public NewsPresenter() {
        attachDataManager(new NewsDataManager());
    }



    @Override
    public void loadData() {
        addSubscribe(mDataManager.getFuliData("20","1")
                .subscribeWith(new CommonSubscriber<GirlsData>(mView,true) {
                    @Override
                    public void onNext(GirlsData girlsData) {
                        mView.setUpView(girlsData);
                    }
                }));
    }

}
