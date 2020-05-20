package com.xiaoqiang.kotlin.baselibs.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.xiaoqiang.library_common.bean.EventBusHelp
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * @author 小强
 *
 * @time 2020/5/7  09:52
 *
 * @desc Presenter基类
 *
 */
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {

    protected var mModel: M? = null
    protected var mView: V? = null

    private val isViewAttached: Boolean
        get() = mView != null

    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * 创建 Model
     */
    open fun createModel(): M? = null


    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }

        if (isBindEventBus()) EventBus.getDefault().register(this)
    }

    override fun detachView() {

        if (isBindEventBus()) EventBus.getDefault().unregister(this)

        // 保证activity结束时取消所有正在执行的订阅
        unDispose()
        mModel?.onDetach()
        this.mModel = null
        this.mView = null
        this.mCompositeDisposable = null
    }

    open fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()  // 保证Activity结束时取消
        mCompositeDisposable = null
    }


    //====================================================== EventBus start =========================================================


    /**
     * 是否使用 EventBus
     */
    open fun isBindEventBus(): Boolean = false

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    open fun onMessageEvent(event: EventBusHelp?) {
        if (event != null) {
            onEvent(event)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    open fun onStickyEventBus(event: EventBusHelp?) {
        if (event != null) {
            onEvent(event)
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected open fun onEvent(event: EventBusHelp?) {}

    //====================================================== EventBus end =========================================================

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        // detachView()
        owner.lifecycle.removeObserver(this)
    }


    open fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }


    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")


}