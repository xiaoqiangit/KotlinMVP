package com.xiaoqiang.kotlin.baselibs.rx

import com.xiaoqiang.library_common.bean.BaseEntity
import com.xiaoqiang.kotlin.baselibs.http.HttpStatus
import com.xiaoqiang.kotlin.baselibs.http.exception.ExceptionHandle
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.utils.NetWorkUtil
import io.reactivex.subscribers.ResourceSubscriber

/**
 * @author 小强
 *
 * @time 2020/5/8 8:38
 *
 * @desc 基本用户
 *
 */
abstract class BaseSubscriber<T : HttpResult<T>> : ResourceSubscriber<T> {

    private var mView: IView? = null
    private var mErrorMsg = ""
    private var bShowLoading = true

    constructor(view: IView) {
        this.mView = view
    }

    constructor(view: IView?, bShowLoading: Boolean) {
        this.mView = view
        this.bShowLoading = bShowLoading
    }

    /**
     * 成功的回调
     */
    protected abstract fun onSuccess(t: T)

    /**
     * 错误的回调
     */
    protected fun onError(t: T) {}

    override fun onStart() {
        super.onStart()
        if (bShowLoading) mView?.showLoading()
        if (!NetWorkUtil.isConnected()) {
            mView?.showDefaultMsg("当前网络不可用，请检查网络设置")
            onComplete()
        }
    }

    override fun onNext(t: T) {
        mView?.hideLoading()
        when {
            t.message == HttpStatus.SUCCESS -> onSuccess(t)
            t.message == HttpStatus.TOKEN_INVALID -> {
                // TODO Token 过期，重新登录
            }
            else -> {
                onError(t)
                if (t.message.isNotEmpty())
                    mView?.showDefaultMsg(t.message)
            }
        }
    }

    override fun onError(e: Throwable) {
        mView?.hideLoading()
        if (mView == null) {
            throw RuntimeException("mView can not be null")
        }
        if (mErrorMsg.isEmpty()) {
            mErrorMsg = ExceptionHandle.handleException(e)
        }
        mView?.showDefaultMsg(mErrorMsg)
    }

    override fun onComplete() {
        mView?.hideLoading()
    }

}