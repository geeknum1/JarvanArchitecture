package com.mustang.jarvanarchitecture;

import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * * Created by Mustang on 2018/8/15.
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，也就是你的应用中原有的主Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApp.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //         如果需要使用MultiDex，需要在此处调用。
        //         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData("25029886-1","9d794db511f28de549715d70c6421ff4","MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCoPlSUS38B1wRMOel/W1rz+3xEPc4IDvH0ufWaolH8OGuQ/cHf9ci5Fv5sNjebFY/xqT7318tDTA6SmSvtZztPTmDfaFqnd826l2ufYXyC/B5OM9QDq/Qjet7oc2ELrGNxyItxO8mSd5MW6BhkZWef/b2DS38i0uTuZxby/BjPRhkLcCvMY0ivPvbxlZjDWS9nAKsl115jTwMNgUisInBAnfC6BdRI3wrQ2qGQ42Xnz0zfPXN9iSxw5BteJdjuxM+DuHPJUQhVEQOG4HPmfBFldBduXsSu6KkOULgRVIo0H+1/ScLVTicZxEgHwiQIYg8NOAeiirSttGFMaA5GRrhBAgMBAAECggEBAIT+cKGhZE6yB6WyCdAxCR7g54SSTrG0DrPj5DBOq/xzJSTCU2WUxwz5DjnASgh/7P+dSDdnEDisgMCF8GGEPzAbxxwTQ8YqQCiF1f9xcpmgbtxmv+v/G8iIMnkR697scNfbLc9e8aKkf1OWOOHz/wxL6mlFXsTKbUm/mDaP7ZS6icTNx3KGLDzErQJv7blTcf2p7RRa8AAmYZSC4QQjYeeqExcB+bllLsxA7rYcNsIrFkpC1H/KcXb7xWOkw3PE9R97n6Elkx0YgbM7922sQy46IA29novw5ol9LpyP5LmMhv6D6Un1UG6jDDvWk91S53Lo/XemX2JkkbvG/h4QmAECgYEA9pskXvLlLJgziyDBBqJfn5l1DJvTXBOrQaDaUy2zKYwcSLwOJlVj+FPPoXZXD3XwcNiWOSquguXjbr02RbA0fWNidt6rCyxXTCGEE7z+n5Ad1KqciVG8s6YK3IRvUxAnGNTFEZK5jK/IeOisJquOHY/jM+F7KdjA6dj8QFhaLOECgYEArqcCwOZF81t/8/f7dbBr0qBe2HPHXXJtEHHWqCJpb2NqleQRltPzDRrgT5PtS7W/d/5EUxtx/CXsPojesw4iiScfMOM67L/8rgm6IfNA5aPlRtPH3SGoq482oJv5cIi0F3NOqbNA/ajaJKeWEz5+O+FJPg2EPu+x2kJ3hrUDl2ECgYEA0GbbncH3GnClXnYXqdueo+mzmSw2uiGFILkmyBRezEWzwWPrMUoIaduqI/E2McKhpzvh9/2x7L0KLpOIQexe73gFxh72vUKDo7rPOz8EjyPjkc187C8/ITK5CxKAYdnMsJde0zMDy3jiGgvHMAba2vC2OSncQ8kBfp34zwfbbQECgYB2llntRq3Kbp9ztTadk5uymUetKvf8gFyDpPg+k5cJlfRiMEitI97NUClsfyHJnW+TdydHUsRTlCBT2bV02K0keVbZaACzjixws5QZx0SSaHWeYOA9SsRvML/D0ARPn9LAdX+9BESlt4ffCZeCG5W07ORBaQYZgcE+VJE5+yCkIQKBgQDT5/6mGynqWnmZW8geVV9liDO2VeVcRdhcmmq+UpK5B/MoC+SsNgUagaAP0R84A+dAh8N/yGfdQFTpWwu8G40Zcbm3PEqjM4PlJdV/WVNNU9KUf/EoV7jjyPnf4yLSR0/69YWURIOT3sSKbH0Go99WUf+dxvhS36mPmX4C5TNQdQ==")
                .setAesKey("0123456789123456")
                .setEnableDebug(false)//默认为false，设为true即调试模式下会输出日志以及不进行补丁签名校验. 线下调试此参数可以设置为true, 它会强制不对补丁进行签名校验, 所有就算补丁未签名或者签名失败也发现可以加载成功. 但是正式发布该参数必须为false, false会对补丁做签名校验, 否则就可能存在安全漏洞风险。
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                            /** 不可以直接Process.killProcess(Process.myPid())来杀进程，这样会扰乱Sophix的内部状态。
                             * 因此如果需要杀死进程，建议使用这个方法，它在内部做一些适当处理后才杀死本进程。*/
                            instance.killProcessSafely();
                        }

                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        Log.e("Sophix",msg);
                    }
                }).initialize();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        /** 补丁在后台发布之后, 并不会主动下行推送到客户端, 客户端通过调用queryAndLoadNewPatch方法查询后台补丁是否可用*/
    }
}
