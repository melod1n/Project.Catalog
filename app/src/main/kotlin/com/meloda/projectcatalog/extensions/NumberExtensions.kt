package com.meloda.projectcatalog.extensions

import com.meloda.projectcatalog.util.AndroidUtils

object NumberExtensions {

    fun Number.dp() = AndroidUtils.dp(this as Int)

}