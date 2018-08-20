package com.mustang.lib_common.utils;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mustang.lib_common.ProgressControlAble;
import com.mustang.lib_common.R;


/**
 * Created by Mustang on 2018/8/15.
 * 公共Loading
 */
public class CommonLoadingDialog extends Dialog implements ProgressControlAble, LifecycleObserver {

    private Context mContext;
    private TextView mTextView;
    private ImageView mLoadingIV;
    private Animation mAnimation;

    public CommonLoadingDialog(Context context) {
        super(context);
        initView(context);
    }

    public CommonLoadingDialog(Context context, int theme) {
        super(context, theme);
        initView(context);
    }

    public CommonLoadingDialog(Context context, boolean cancelable,
                               OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    @Override
    public void show() {
        super.show();
        if (Utils.activityFinished(mContext)) {
            return;
        }
        mAnimation = AnimationUtils.loadAnimation(mContext,
                R.anim.anim_loading);
        mLoadingIV.setAnimation(mAnimation);
    }

    @Override
    public void dismiss() {
        if (Utils.activityFinished(mContext)) {
            return;
        }
        if (mAnimation != null) {
            mAnimation.cancel();
            mAnimation = null;
        }
        super.dismiss();
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        setContentView(view);

        mLoadingIV = (ImageView) findViewById(R.id.imageView12);
        mTextView = (TextView) findViewById(R.id.tv_content);

        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    public void setMessage(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            mTextView.setText("数据加载中");
            return;
        }
        mTextView.setText(text);
    }

    @Override
    public void showProgress() {
        show();
    }

    @Override
    public void hideProgress() {
        dismiss();
    }
}
