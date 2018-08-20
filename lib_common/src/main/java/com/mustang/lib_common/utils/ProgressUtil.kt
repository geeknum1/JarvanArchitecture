package com.mustang.lib_common.utils

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.mustang.lib_common.ProgressControlAble

/**
 * Created by Mustang on 2018/8/15.
 */
class ProgressUtil(val activity: Activity) : ProgressControlAble, LifecycleObserver {

    private val circularProgressBar: ProgressBar
        get() {
            return ProgressBar(activity)
        }

    private var frameLayout: FrameLayout

    init {
        val decorView = activity.window.decorView as FrameLayout
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER)
        frameLayout = FrameLayout(activity)
        val layoutParams2 = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        frameLayout.addView(circularProgressBar, layoutParams2)
        frameLayout.setBackgroundColor(Color.TRANSPARENT)
        frameLayout.setOnClickListener {
            hideProgress()
        }
        decorView.addView(frameLayout, layoutParams)
        frameLayout.visibility = View.GONE
    }

    override fun showProgress() {
        frameLayout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        frameLayout.visibility = View.GONE
    }
}