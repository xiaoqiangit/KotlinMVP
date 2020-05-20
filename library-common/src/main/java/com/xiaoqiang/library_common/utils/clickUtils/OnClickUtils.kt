package com.xiaoqiang.library_common.utils.clickUtils

/**
 * @author 小强
 *
 * @time 2020/5/8  16:07
 *
 * @desc 防止连续点击
 *
 */
object OnClickUtils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private const val MIN_CLICK_DELAY_TIME = BaseConstant.CLICK_TIME
    private var lastClickTime: Long = 0

    //返回为true 说明可以点击 返回false 说明是连续点击
    fun isFastClick(): Boolean {
        var flag = false
        val curClickTime = System.currentTimeMillis()

        if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            flag = true
        }
        lastClickTime = curClickTime
        return flag
    }

    //返回为true 说明可以点击 返回false 说明是连续点击 不需要提示
    fun isFastClickNotToast(): Boolean {
        var flag = false
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            flag = true
        }
        lastClickTime = curClickTime
        return flag
    }
}