package com.mustang.jarvanarchitecture;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mustang.jarvanarchitecture.adapter.FragmentAdapter;
import com.mustang.lib_common.base.ARouterPath;
import com.mustang.lib_common.base.BaseActivity;
import com.mustang.lib_common.base.BaseFragment;
import com.mustang.lib_common.base.SimpleActivity;
import com.mustang.lib_common.base.SimpleFragment;
import com.mustang.lib_common.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Mustang on 2018/8/16.
 */

public class MainActivity extends SimpleActivity {

    NoScrollViewPager mContainerPager;
    BottomNavigationView mNavigation;
    private List<SimpleFragment> mFragments = new ArrayList<>();
    private FragmentAdapter mAdapter;




    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mContainerPager = (NoScrollViewPager)findViewById(R.id.container_pager);
        mNavigation = (BottomNavigationView)findViewById(R.id.navigation);


        mContainerPager.setOffscreenPageLimit(2);

        SimpleFragment fragmentNews = (SimpleFragment) ARouter.getInstance().build(ARouterPath.NewsFgt).navigation();
        SimpleFragment fragmentGirls = (SimpleFragment) ARouter.getInstance().build(ARouterPath.GirlsFgt).navigation();

        mFragments.add(fragmentNews);
        mFragments.add(fragmentGirls);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mContainerPager.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.navigation_home) {
                    mContainerPager.setCurrentItem(0);
                    return true;
                } else if (i == R.id.navigation_dashboard) {
                    mContainerPager.setCurrentItem(1);
                    return true;
                }
                return false;
            }

        });
    }


}
