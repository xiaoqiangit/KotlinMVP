package com.debug

import android.app.Application
import com.xiaoqiang.library_common.config.AppConfig

/**
 * @author 小强
 *
 * @time 2020/5/12  11:28
 *
 * @desc 我的Application
 *
 */
class MineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }

}