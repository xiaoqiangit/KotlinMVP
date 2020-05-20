package com.xiaoqiang.kotlin.baselibs.rx

import com.xiaoqiang.kotlin.baselibs.rx.scheduler.IoMainScheduler

/**
 * @author 小强
 *
 * @time 2020/5/7  11:15
 *
 * @desc 调度器工具类
 *
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()

}