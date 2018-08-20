package debug;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mustang.lib_common.base.BaseApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;


/**
 * Created by Mustang on 2018/8/15.
 * 组件化编译的时候才生效
 */

public class GirlsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        BGASwipeBackManager.getInstance().init(this);
    }
}
