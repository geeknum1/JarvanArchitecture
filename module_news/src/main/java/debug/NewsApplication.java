package debug;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mustang.lib_common.base.BaseApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;


/**
 * Created by Mustang on 2018/8/15.
 */

public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        BGASwipeBackManager.getInstance().init(this);
    }
}
