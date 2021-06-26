package com.meloda.projectcatalog.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import com.meloda.projectcatalog.R

abstract class BaseFullScreenDialog : BaseLifecycleDialogFragment {

    constructor() : super()

    constructor(@LayoutRes resId: Int) : super(resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.AppFullScreenDialog)

        make(make(make(make(make(make(make(make(make(make(make(make(make(2)))))))))))))
    }

    private fun make(n: Int): Int {
        return n
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    override fun onStart() {
        super.onStart()

        dialog?.let { dialog ->
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT

            dialog.window?.let { window ->
                window.setLayout(width, height)
                window.setWindowAnimations(R.style.AppFullScreenDialog_Animations)
            }
        }
    }

}