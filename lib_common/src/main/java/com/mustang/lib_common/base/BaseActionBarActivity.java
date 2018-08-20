/*
 *  @ProjectName: ISMS_Petrel_MCU
 *  @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 *  @address: http://www.hikvision.com
 *  @Description: 本内容仅限于杭州海康威视系统技术公有限司内部使用，禁止转发.
 */

package com.mustang.lib_common.base;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.StringRes;


/**
 * * Created by Mustang on 2018/8/15.
 * BaseActionBarActivity继承于BaseActivity，封装了actionBar的逻辑；
 * 继承于ActionBarBaseActivity的Activity都将默认带有ActionBar，并且只能使用AppTheme主题；
 * 只有那些ActionBar只带有Title和返回按钮的Activity方可继承
 * @name BaseActionBarActivity
 */
public abstract class BaseActionBarActivity extends BaseActivity {

    /*默认的ActionBar*/
    protected ActionBar mActionBar;

    /**
     * 设置默认标题id
     *
     * @return 标题id
     */
    @StringRes
    protected abstract int setTitleId();


    /**
     * 更新标题
     *
     * @param title String标题
     */
    protected void setTitle(String title) {
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
