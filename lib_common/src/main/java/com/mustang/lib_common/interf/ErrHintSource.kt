package com.mustang.lib_common

import com.mustang.lib_common.utils.Utils


/**
 * Created by Mustang on 2018/8/15.
 */
interface ErrHintSource {
    fun getHint(err: String):String?
    val Int.getString: String
        get() {
            return Utils.getString(this)
        }
}