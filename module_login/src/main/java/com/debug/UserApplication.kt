package com.debug

import android.app.Application
import com.xiaoqiang.library_common.config.AppConfig


/**
 * @author 小强
 *
 * @time 2020/5/13 10:10
 *
 * @desc 登录的Application
 *
 */
class LoginApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
    }

}