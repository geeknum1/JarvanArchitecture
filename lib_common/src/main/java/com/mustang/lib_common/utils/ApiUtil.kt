package com.mustang.lib_common.utils

import com.mustang.lib_common.utils.ApiEnum
import com.mustang.lib_common.utils.RxUtil
import io.reactivex.Flowable
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mustang on 2018/8/15.
 */

class ApiUtil {
    @Suppress("UNCHECKED_CAST")
    private fun <T> getApi(clazz: Class<*>, host: String, okHttpClient: OkHttpClient): T {
        return Retrofit.Builder().baseUrl(host).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(clazz) as T
    }

    fun <T> getApi(clazz: Class<*>, apiEnum: ApiEnum): T {
        return getApi(clazz, apiEnum.host, apiEnum.getHttpClient())
    }

    companion object {
        fun createRequestBody(jsonObject: JSONObject): RequestBody {
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString())
        }

        fun<T> getComposeFlowable(flowable: Flowable<T>): Flowable<T> {
            return flowable.compose(RxUtil.rxSchedulerHelper())
        }
    }

}
