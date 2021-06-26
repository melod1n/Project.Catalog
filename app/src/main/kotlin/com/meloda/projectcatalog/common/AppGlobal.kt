package com.meloda.projectcatalog.common

import android.app.Application
import android.view.inputmethod.InputMethodManager

class AppGlobal : Application() {

    companion object {
        lateinit var inputMethodManager: InputMethodManager
    }

    override fun onCreate() {
        super.onCreate()

        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

    }

}