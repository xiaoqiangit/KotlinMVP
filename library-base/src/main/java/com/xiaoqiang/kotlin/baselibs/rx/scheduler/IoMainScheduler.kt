package com.xiaoqiang.kotlin.baselibs.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author 小强
 *
 * @time 2020/5/7  09:20
 *
 * @desc 子线程调度到主线程调度器
 *
 */
class IoMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())
