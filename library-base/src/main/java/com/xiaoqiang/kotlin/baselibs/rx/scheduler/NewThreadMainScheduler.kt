package com.xiaoqiang.kotlin.baselibs.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author 小强
 *
 * @time 2020/5/7  09:25
 *
 * @desc 新建一个线程
 *
 */
class NewThreadMainScheduler<T> private constructor() :
    BaseScheduler<T>(Schedulers.newThread(), AndroidSchedulers.mainThread())
