package com.mustang.module_news.data

import io.reactivex.Flowable

/**
 * Created by Mustang on 2018/8/15.
 */
interface NewsDataSource {
    fun login(name: String, pwd: String): Flowable<*>?
    fun loadData(): Flowable<*>?
    fun getFuliData(size: String, page: String ): Flowable<*>?
}