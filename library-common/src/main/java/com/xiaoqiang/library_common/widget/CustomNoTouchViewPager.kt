package com.xiaoqiang.library_common.widget

import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * @author 小强
 *
 * @time 2020/5/11  17:13
 *
 * @desc 禁止viewPager滑动
 *
 */
class CustomNoTouchViewPager : ViewPager {


    constructor(@NonNull context: Context) : this(context, null)


    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}