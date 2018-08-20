package com.mustang.lib_common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Mustang on 2018/8/15.
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private CompositeDisposable mCompositeDisposable;
    private ProgressBar mProgressBar;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
        super.onAttach(context);
    }

    protected void initProgress() {
        if (mProgressBar == null) {
            mProgressBar = new ProgressBar(_mActivity);
        }
        ((ViewGroup) getView().getParent()).addView(mProgressBar);
    }

    protected void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }


    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEventAndData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.dispose();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();






}
