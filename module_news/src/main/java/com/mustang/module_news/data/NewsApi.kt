package com.mustang.module_news.data
import com.mustang.lib_common.LoginBean
import com.mustang.module_news.bean.GirlsData
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Mustang on 2018/8/15.
 */
interface NewsApi {

    @POST("xxxx.php")
    fun login(@Body requestBody: RequestBody): Flowable<LoginBean>

    @GET("api/data/福利/{size}/{index}")
    fun getFuliData(@Path("size") size: String, @Path("index") index: String): Flowable<GirlsData>

}