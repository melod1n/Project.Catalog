package com.meloda.projectcatalog.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView
import com.meloda.projectcatalog.R
import com.meloda.projectcatalog.util.AndroidUtils
import kotlin.math.roundToInt

@Suppress("ConvertSecondaryConstructorToPrimary")
class DividerItemDecoration : RecyclerView.ItemDecoration {
    companion object {
        private val ATTRS = intArrayOf(R.attr.dividerHorizontal)
    }

    private lateinit var divider: Drawable

    private var marginStart: Int = 0
    private var marginEnd: Int = 0

    //default drawable
    /**
     * @param marginStart in dpi
     * @param marginEnd in dpi
     */
    constructor(
        context: Context,
        @Dimension(unit = Dimension.DP) marginStart: Int = 0,
        @Dimension(unit = Dimension.DP) marginEnd: Int = 0
    ) {
        this.marginEnd = marginEnd
        this.marginStart = marginStart
        val styledAttributes = context.obtainStyledAttributes(ATTRS)
        divider = styledAttributes.getDrawable(0) ?: return
        styledAttributes.recycle()
    }

    //custom drawable
//    constructor(context: Context, resId: Int) {
//        divider = ContextCompat.getDrawable(context, resId) ?: return
//    }

    //set items' margins
//    override fun getItemOffsets(
//        outRect: Rect,
//        view: View,
//        parent: RecyclerView,
//        state: RecyclerView.State
//    ) {
//        super.getItemOffsets(outRect, view, parent, state)
//
////        if (parent.getChildAdapterPosition(view) == 0) return
//
//        outRect.right = AndroidUtils.px(16).roundToInt()
//        outRect.left = AndroidUtils.px(16).roundToInt()
//    }


    //set dividers' margins
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        val paddingInPx = AndroidUtils.px(16).roundToInt()
//        val right = parent.width - paddingInPx

        val paddingInPx = AndroidUtils.px(marginStart).roundToInt()
        val right = parent.width - AndroidUtils.px(marginEnd).roundToInt()

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin

            val bottom = top + divider.intrinsicHeight

            divider.setBounds(paddingInPx, top, right, bottom)
            divider.draw(c)
        }
    }


}