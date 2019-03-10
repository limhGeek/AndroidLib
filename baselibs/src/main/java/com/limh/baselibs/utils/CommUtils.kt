package com.limh.baselibs.utils

import android.view.View

/**
 * @author limh
 * @function
 * @date 2019/3/10 15:00
 */
object CommUtils {

     fun measureWidthAndHeight(view: View) {
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(widthMeasureSpec, heightMeasureSpec)
    }
}