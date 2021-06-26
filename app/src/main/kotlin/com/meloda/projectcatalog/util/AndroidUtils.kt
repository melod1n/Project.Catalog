package com.meloda.projectcatalog.util

import android.content.res.Resources
import android.util.DisplayMetrics

object AndroidUtils {

    fun px(dp: Float): Float {
        return dp * (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun px(dp: Int) = px(dp.toFloat())

    fun dp(px: Float): Float {
        return px / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun dp(px: Int) = dp(px.toFloat())

}