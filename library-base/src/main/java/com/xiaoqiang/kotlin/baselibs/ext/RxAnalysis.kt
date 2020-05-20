package com.xiaoqiang.kotlin.baselibs.ext

import android.annotation.SuppressLint
import com.orhanobut.logger.Logger
import com.xiaoqiang.kotlin.baselibs.http.HttpStatus
import com.xiaoqiang.kotlin.baselibs.http.exception.ExceptionHandle
import com.xiaoqiang.kotlin.baselibs.http.function.RetryWithDelay
import com.xiaoqiang.kotlin.baselibs.mvp.IModel
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.kotlin.baselibs.rx.SchedulerUtils
import com.xiaoqiang.library_common.bean.BaseEntity
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.utils.NetWorkUtil
import com.xiaoqiang.library_common.widget.ToastUtils
import com.zchu.rxcache.RxCache
import com.zchu.rxcache.kotlin.load
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlin.reflect.jvm.javaType

/**
 * @author 小强
 *
 * @time 2020/5/7  16:05
 *
 * @desc 请求服务器数据解析返回处理
 *
 */

/**
 * 有缓存的请求
 */
fun <T : BaseEntity> Observable<T>.requestCache(
        model : IModel?,
        view : IView?,
        isShowLoading : Boolean = true,
        onSuccess : (T) -> Unit,
        onCache : (() -> Unit)? = null,
        onError : ((T) -> Unit)? = null
) {
    this.compose(SchedulerUtils.ioToMain())
            .retryWhen(RetryWithDelay())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    view?.hideLoading()
                }

                override fun onSubscribe(d : Disposable) {
                    if (isShowLoading) view?.showLoading()
                    model?.addDisposable(d)
                    if (!NetWorkUtil.isConnected()) {
                        view?.showDefaultMsg("当前网络不可用，使用的是缓存")
                        d.dispose()
                        onComplete()
                        onCache?.invoke()
                    }
                }

                override fun onNext(t : T) {
                    view?.hideLoading()
                    Logger.d("t---->:$t")
                    when (t.code) {
                        HttpStatus.SUCCESS -> {
                            RxCache.getDefault().save("custom_key", t)?.subscribe()
                            onSuccess.invoke(t)
                        }
                        HttpStatus.TOKEN_INVALID -> {
                            // Token 过期，重新登录
                            ToastUtils.showToast("Token 过期，重新登录")
                        }
                        else -> {
                            if (onError != null) {
                                onError.invoke(t)
                            } else {
                                Logger.d("t.message---->:${t.message}")
                                if (t.message.isNotEmpty()) view?.showDefaultMsg(t.message)
                            }
                        }
                    }
                }

                override fun onError(t : Throwable) {
                    Logger.d("---->:$t")
                    view?.hideLoading()
                    view?.showError(ExceptionHandle.handleException(t))
                }
            })
}


fun <T : BaseEntity> Observable<T>.requestModel(
        model : IModel?,
        view : IView?,
        isShowLoading : Boolean = true,
        onSuccess : (T) -> Unit,
        onError : ((T) -> Unit)? = null
) {
    this.compose(SchedulerUtils.ioToMain())
            .retryWhen(RetryWithDelay())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    view?.hideLoading()

                }

                @SuppressLint("CheckResult")
                override fun onSubscribe(d : Disposable) {
                    if (isShowLoading) view?.showLoading()
                    model?.addDisposable(d)

                    if (!NetWorkUtil.isConnected()) {
                        view?.showDefaultMsg("当前网络不可用，请检查网络设置")
                        d.dispose()
                        onComplete()


                    }
                }

                override fun onNext(t : T) {
                    view?.hideLoading()
                    Logger.e("213333333333333---->:$t")
                    when (t.code) {
                        HttpStatus.SUCCESS -> {
                            Logger.d("t---->:$t")
                            RxCache.getDefault().save("custom_key", t)?.subscribe()
                            onSuccess.invoke(t)
                        }
                        HttpStatus.TOKEN_INVALID -> {
                            // Token 过期，重新登录
                            ToastUtils.showToast("Token 过期，重新登录")
                        }
                        else -> {
                            if (onError != null) {
                                onError.invoke(t)
                            } else {
                                Logger.d("t.message---->:${t.message}")
                                if (t.message.isNotEmpty()) view?.showDefaultMsg(t.message)
                            }
                        }
                    }
                }

                override fun onError(t : Throwable) {
                    view?.hideLoading()
                    view?.showError(ExceptionHandle.handleException(t))
                }
            })
}


fun <T : BaseEntity> Observable<T>.requestModel(
        view : IView?,
        isShowLoading : Boolean = true,
        onSuccess : (T) -> Unit,
        onError : ((T) -> Unit)? = null
) : Disposable {
    if (isShowLoading) view?.showLoading()
    return this.compose(SchedulerUtils.ioToMain())
            .retryWhen(RetryWithDelay())
            .subscribe({
                if (isShowLoading) view?.showLoading()
                when (it.code) {
                    HttpStatus.SUCCESS -> onSuccess.invoke(it)
                    HttpStatus.TOKEN_INVALID -> {
                        // Token 过期，重新登录
                    }
                    else -> {
                        if (onError != null) {
                            onError.invoke(it)
                        } else {
                            if (it.message.isNotEmpty())
                                view?.showDefaultMsg(it.message)
                        }
                    }
                }
            }, {
                view?.hideLoading()
                view?.showError(ExceptionHandle.handleException(it))
            }, {
                view?.hideLoading()

            }, {
                if (isShowLoading) view?.showLoading()
                if (!NetWorkUtil.isConnected()) {
                    view?.showDefaultMsg("当前网络不可用，请检查网络设置")
                    it.dispose()
                    view?.hideLoading()
                }

            })
}

