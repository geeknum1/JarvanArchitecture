package com.mustang.lib_common

import android.view.View

/**
 * Created by Mustang on 2018/8/15.
 */
interface BottomDialogControlAble{
    fun showBottomDialog(view: View)
    fun dismissAllBottomDialog()
    fun dismissBottomDialog(view: View)
}