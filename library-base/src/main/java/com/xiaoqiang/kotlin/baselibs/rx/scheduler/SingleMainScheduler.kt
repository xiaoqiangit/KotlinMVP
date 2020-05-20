package com.xiaoqiang.kotlin.baselibs.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author 小强
 *
 * @time 2020/5/7  11:00
 *
 * @desc 单个线程
 *
 */
class SingleMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.single(), AndroidSchedulers.mainThread())
