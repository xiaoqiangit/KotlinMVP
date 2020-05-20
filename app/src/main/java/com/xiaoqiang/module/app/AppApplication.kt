package com.xiaoqiang.module.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.xiaoqiang.library_common.config.AppConfig

/**
 * @author 小强
 *
 * @time 2020/5/10 9:52
 *
 * @desc App的Application
 *
 */
class AppApplication : Application() {

    private var refWatcher: RefWatcher? = null

    companion object {
        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as AppApplication
            return app.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        AppConfig.init(this)
        AppConfig.openDebug()


        //内存泄露检测
        initLeakCanary()

    }


    /**
     * 内存泄露检测
     */
    private fun initLeakCanary() {
        refWatcher = if (LeakCanary.isInAnalyzerProcess(this))
            RefWatcher.DISABLED
        else LeakCanary.install(this)

        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    }

}