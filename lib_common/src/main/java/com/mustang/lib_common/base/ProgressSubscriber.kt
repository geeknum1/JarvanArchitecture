package com.mustang.lib_common.widget

import com.mustang.lib_common.ProgressEnum
import com.mustang.lib_common.base.RxBus
import com.mustang.lib_common.event.InternetErrorEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Created by Mustang on 2018/8/15.
 */
abstract class ProgressSubscriber<T>(private val compositeDisposable: CompositeDisposable) : Subscriber<T> {
    override fun onError(t: Throwable) {
        RxBus.getDefault().post(InternetErrorEvent(t.message))
        RxBus.getDefault().post(ProgressEnum.HIDE)
    }

    override fun onSubscribe(s: Subscription) {
        RxBus.getDefault().post(ProgressEnum.SHOW)
        s.request(Long.MAX_VALUE)
        compositeDisposable.add(Disposables.fromSubscription(s))
    }

    override fun onComplete() {
        RxBus.getDefault().post(ProgressEnum.HIDE)
    }

}