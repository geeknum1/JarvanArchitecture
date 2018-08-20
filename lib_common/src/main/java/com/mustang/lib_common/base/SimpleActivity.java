package com.mustang.lib_common.base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mustang.lib_common.ActivityEnum;
import com.mustang.lib_common.ProgressControlAble;
import com.mustang.lib_common.ProgressEnum;
import com.mustang.lib_common.R;
import com.mustang.lib_common.event.InternetErrorEvent;
import com.mustang.lib_common.utils.CommonLoadingDialog;
import com.mustang.lib_common.utils.ProgressUtil;
import com.mustang.lib_common.utils.ToastUtils;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Mustang on 2018/8/15.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity implements BGASwipeBackHelper.Delegate {
    protected BGASwipeBackHelper mSwipeBackHelper;
    protected static Activity mContext;
    private CompositeDisposable mCompositeDisposable;
    private ProgressControlAble mProgressControlAble;
    private EventListener eventListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        eventListener = new EventListener();
        getLifecycle().addObserver(eventListener);
        mProgressControlAble = new CommonLoadingDialog(this,R.style.MyDialogStyle);
        mContext = this;
        mCompositeDisposable = new CompositeDisposable();
        BaseApplication.getInstance().addActivity(this);
        initEventAndData();
        initListener();
    }


    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
        BaseApplication.getInstance().removeActivity(this);
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();


    protected abstract void initListener();

    private void showProgress() {
        mProgressControlAble.showProgress();
    }

    private  void hideProgress() {
        mProgressControlAble.hideProgress();
    }
    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

  /*  @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }*/


    class EventListener implements LifecycleObserver {
        private CompositeDisposable compositeDisposable = new CompositeDisposable();


        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void initDisposable() {
            compositeDisposable.addAll(RxBus.getDefault().toDefaultFlowable(ProgressEnum.class, new Consumer<ProgressEnum>() {
                @Override
                public void accept(ProgressEnum progressEnum) throws Exception {
                    if (progressEnum == ProgressEnum.HIDE) {
                        mProgressControlAble.hideProgress();
                    }
                    if (progressEnum == ProgressEnum.SHOW) {
                        mProgressControlAble.showProgress();
                    }
                }
            }), RxBus.getDefault().toDefaultFlowable(ActivityEnum.class, new Consumer<ActivityEnum>() {
                @Override
                public void accept(ActivityEnum activityEnum) throws Exception {
                    if (ActivityEnum.Exit == activityEnum) {
                        finish();
                    }
                }
            }), RxBus.getDefault().toDefaultFlowable(InternetErrorEvent.class, new Consumer<InternetErrorEvent>() {
                @Override
                public void accept(InternetErrorEvent internetErrorEvent) throws Exception {
                    ToastUtils.showLongToast(internetErrorEvent.getMsg());
                }
            }));

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void releaseDisposable() {
            mProgressControlAble.hideProgress();
            compositeDisposable.clear();
        }
    }
}
