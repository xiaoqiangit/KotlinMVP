package com.xiaoqiang.kotlin.baselibs.mvp

/**
 * @author 小强
 *
 * @time 2020/5/7  09:45
 *
 * @desc Presenter基类接口
 *
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()

}