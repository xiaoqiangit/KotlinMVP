package com.xiaoqiang.kotlin.baselibs.mvp

import io.reactivex.disposables.Disposable


/**
 * @author 小强
 *
 * @time 2020/5/7  09:40
 *
 * @desc IModel基类接口
 *
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()

}