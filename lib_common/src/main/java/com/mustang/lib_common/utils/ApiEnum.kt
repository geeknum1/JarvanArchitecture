package com.mustang.lib_common.utils

import com.mustang.lib_common.base.Constants
import okhttp3.OkHttpClient

/**
 * Created by Mustang on 2018/8/15.
 */
enum class ApiEnum(val host: String) {
    Shop(Constants.SHOP_HOST) {
        override fun getHttpClient(): OkHttpClient {
            return OkHttpUtil.getHttpClient()
        }
    },
    MemberCenter(Constants.PERSON_HOST) {
        override fun getHttpClient(): OkHttpClient {
            return OkHttpUtil.getHttpClient()
        }
    };

    abstract fun getHttpClient(): OkHttpClient
}