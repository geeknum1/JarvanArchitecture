package com.mustang.lib_common

import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Mustang on 2018/8/15.
 */
interface MemberInfoApi {

    @POST("mobileapi.php")
    fun login(@Body requestBody: RequestBody): Flowable<LoginBean>

}