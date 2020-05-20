package com.xiaoqiang.kotlin.baselibs.rx.scheduler


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author 小强
 *
 * @time 2020/5/7  11:15
 *
 * @desc TrampolineMainScheduler调度
 *
 */
class TrampolineMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.trampoline(), AndroidSchedulers.mainThread())
