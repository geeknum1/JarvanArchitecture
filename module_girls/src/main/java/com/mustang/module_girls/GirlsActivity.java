package com.mustang.module_girls;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.mustang.lib_common.base.ARouterPath;
import com.mustang.lib_common.base.SimpleActivity;

@Route(path = ARouterPath.GirlsAty)
public class GirlsActivity extends SimpleActivity {



    @Override
    protected int getLayout() {
        return R.layout.activity_girls;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initListener() {

    }
}
