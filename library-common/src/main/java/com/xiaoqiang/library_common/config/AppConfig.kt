package com.xiaoqiang.library_common.config

import android.R
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.xiaoqiang.library_common.BuildConfig
import com.xiaoqiang.library_common.utils.DynamicTimeFormat
import me.jessyan.autosize.AutoSize


/**
 * @author 小强
 *
 * @time 2020/5/7  23:00
 *
 * @desc 用来初始化项目所需要的配置
 *
 */
object AppConfig {

    var debug = false

    private var application: Application? = null

    /**
     * 初始化
     */
    fun init(application: Application) {
        this.application = application

        //路由初始化
        initRouter()

        //初始化下来刷新布局
        initSmartRefreshLayout()

        //初始化日志
        initLogger()

        //今日头条适配
        AutoSize.initCompatMultiProcess(application)
    }

    fun getApplication(): Application {
        if (application == null) {
            throw RuntimeException("先去初始化")
        }
        return application!!
    }

    fun openDebug() {
        debug = true
    }


    /**
     * 初始化日志
     */
    private fun initLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(1) // (Optional) How many method line to show. Default 2
            .methodOffset(0) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("hzq") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                //                return BuildConfig.DEBUG; //关闭打印日志设置为false
                return debug //关闭打印日志设置为false
            }
        })
    }



    /**
     * 路由初始化
     */
    private fun initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }
    /**
     * 初始化下来刷新布局
     */
    private fun initSmartRefreshLayout() {
        //启用矢量图兼容
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout -> //全局设置（优先级最低）
            layout.setEnableLoadMore(false)
            layout.setEnableAutoLoadMore(true)
            layout.setEnableOverScrollDrag(true)
            layout.setEnableOverScrollBounce(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
            layout.setEnableScrollContentWhenRefreshed(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
            layout.setPrimaryColorsId(R.color.white, R.color.primary_text_light)
            ClassicsHeader(context).setTimeFormat(DynamicTimeFormat("更新于 %s"))
        }
    }



}