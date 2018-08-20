package com.mustang.lib_common.utils;

import com.mustang.lib_common.BuildConfig;
import com.mustang.lib_common.base.Constants;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Mustang on 2018/8/20.
 */

public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpUtil.class) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(20, TimeUnit.SECONDS);
                    builder.readTimeout(20, TimeUnit.SECONDS);
                    builder.writeTimeout(20, TimeUnit.SECONDS);
                    builder.retryOnConnectionFailure(true);
                    if (!Constants.isOnline) {
                        builder.addInterceptor(new ChuckInterceptor(Utils.getContext()));
                    }
                    mOkHttpClient = builder//.addInterceptor(new UserAgentInterceptor(Constants.USER_AGENT))
                            .addNetworkInterceptor(new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
//                                    dos.writeBytes(DATA_STR);
                                    return chain.proceed(chain.request());
                                }
                            })
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }






}
