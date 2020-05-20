package com.xiaoqiang.kotlin.baselibs.base

import android.view.View
import com.xiaoqiang.kotlin.baselibs.mvp.IPresenter
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.library_common.widget.ToastUtils.showToast

/**
 * @author 小强
 *
 * @time  2020/5/7  17:02
 *
 * @desc BaseMvpFragment
 *
 */
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    /**
     * 创建当前Presenter
     */

    protected abstract fun createPresenter(): P

    /**
     * 初始化 View
     */
    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        this.mPresenter = null
    }


    /**
     * 加载中...
     */
    override fun showLoading() {
    }

    /**
     * 隐藏load
     */
    override fun hideLoading() {
    }

    /**
     * Toast显示错误信息
     * @param errorMsg 错误信息
     */
    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }

    /**
     * Toast显示默认的提示(如:网络错误,token失效等)
     * @param msg: 默认信息
     */
    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }


    /**
     * Toast显示信息
     * @param msg 需要的信息
     */
    override fun showMsg(msg: String) {
        showToast(msg)
    }
}