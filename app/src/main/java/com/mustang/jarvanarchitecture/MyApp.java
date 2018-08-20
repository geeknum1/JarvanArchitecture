package com.mustang.jarvanarchitecture;



import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.mustang.lib_common.base.BaseApplication;
import com.mustang.lib_common.utils.Utils;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by Mustang on 2018/8/15.
 */

public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if (Utils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        BGASwipeBackManager.getInstance().init(this);
    }
}
