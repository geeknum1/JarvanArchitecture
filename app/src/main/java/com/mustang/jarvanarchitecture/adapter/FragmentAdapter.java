package com.mustang.jarvanarchitecture.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mustang.lib_common.base.BaseFragment;
import com.mustang.lib_common.base.SimpleFragment;

import java.util.List;


/**
 *
 * Created by Mustang on 2018/8/15.
 * <p>Fragments适配器 </p>
 * @name ResourcePagerAdapter
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<SimpleFragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<SimpleFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
