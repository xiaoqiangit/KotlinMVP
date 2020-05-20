package com.xiaoqiang.kotlin.baselibs.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author 小强
 *
 * @time 2020/5/7  09:20
 *
 * @desc ComputationMainScheduler调度器
 *
 */
class ComputationMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())
