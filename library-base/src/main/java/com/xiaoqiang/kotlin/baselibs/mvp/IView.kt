package com.xiaoqiang.kotlin.baselibs.mvp

/**
 * @author 小强
 *
 * @time 2020/5/7  09:24
 *
 * @desc View基类接口
 *
 */
interface IView {
    /**
     * 显示加载
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 使用默认的样式显示信息: CustomToast
     */
    fun showDefaultMsg(msg: String)

    /**
     * 显示信息
     */
    fun showMsg(msg: String)

    /**
     * 显示错误信息
     */
    fun showError(errorMsg: String)


}