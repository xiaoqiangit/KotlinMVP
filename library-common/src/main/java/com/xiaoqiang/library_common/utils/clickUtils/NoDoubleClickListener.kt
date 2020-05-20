package com.xiaoqiang.library_common.utils.clickUtils

import android.view.View


/**
 * @author 小强
 *
 * @time 2020/5/7  14:15
 *
 * @desc 防止连续点击
 *
 */
abstract class NoDoubleClickListener : View.OnClickListener {

    private var mThrottleFirstTime: Long = BaseConstant.CLICK_TIME
    private var mLastClickTime: Long = 0

    constructor()

    constructor(throttleFirstTime: Long) {
        mThrottleFirstTime = throttleFirstTime
    }

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - mLastClickTime > mThrottleFirstTime) {
            mLastClickTime = currentTime
            onNoDoubleClick(v)
        }
    }

    abstract fun onNoDoubleClick(v: View?)

}



