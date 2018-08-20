package com.mustang.module_news.contract;


import com.mustang.lib_common.base.BasePresenter;
import com.mustang.lib_common.base.BaseView;
import com.mustang.module_news.bean.GirlsData;
import com.mustang.module_news.data.NewsDataManager;

/**
 * Created by qht on 16/8/9.
 * Email:569877490@qq.com
 */

public interface NewsContract {

    interface View extends BaseView {

        void setUpView(GirlsData girlsData);



    }

    interface  Presenter extends BasePresenter<View,NewsDataManager> {

        void loadData();

    }
}
