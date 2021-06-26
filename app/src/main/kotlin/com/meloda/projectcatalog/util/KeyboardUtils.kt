package com.meloda.projectcatalog.util

import android.view.View
import android.view.inputmethod.InputMethodManager
import com.meloda.projectcatalog.common.AppGlobal

object KeyboardUtils {

    fun hideKeyboard(focusedView: View) {
        AppGlobal.inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
    }

    fun showKeyboard(focusedView: View) {
        AppGlobal.inputMethodManager.showSoftInput(focusedView, InputMethodManager.SHOW_FORCED)
    }

}