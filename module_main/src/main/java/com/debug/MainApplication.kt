package com.debug

import android.app.Application
import com.xiaoqiang.library_common.config.AppConfig

/**
 * @author 小强
 *
 * @time 2020/5/10 9:52
 *
 * @desc 主页的Application
 *
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }

}