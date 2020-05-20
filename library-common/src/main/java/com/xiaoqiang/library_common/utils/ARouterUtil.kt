package com.xiaoqiang.library_common.utils

import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author 小强
 *
 * @time 2020/5/13  15:54
 *
 * @desc 阿里路由工具类
 *
 */

object ARouterUtil {
    fun getARouter(path: String): Any? {
        return ARouter.getInstance().build(path).navigation()
    }
}
