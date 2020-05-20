package com.xiaoqiang.kotlin.baselibs.base

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xiaoqiang.kotlin.baselibs.R
import com.xiaoqiang.library_common.bean.EventBusHelp
import com.xiaoqiang.library_common.utils.EventBusUtil
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author 小强
 *
 * @time  2020/5/7  17:02
 *
 * @desc BaseFragment基类
 *
 */
abstract class BaseFragment : Fragment() {

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false

    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(attachLayoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isBindEventBus()) EventBusUtil.register(this)
        isViewPrepare = true
        //初始化沉浸式
        if (isImmersionBarEnabled()) initImmersionBar()
        initView(view)
        initData()
        lazyLoadDataIfPrepared()
        initEvent()



    }


    /**
     * 获取权限处理类
     */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }


    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun attachLayoutId(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    open fun initView(view: View) {}


    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    /**
     * 初始化 事件
     */
    open fun initEvent() {}



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


    //====================================================== 沉浸式相关 start =========================================================
    /**
     *
     * 是否可以使用沉浸式
     *
     * @return ture-->使用 false-->不使用
     */
    protected open fun isImmersionBarEnabled(): Boolean = true


    /**
     * 初始化沉浸式
     */
    protected open fun initImmersionBar() {

        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(true)
            statusBarColor(R.color.base_white)
        }
    }


    /**
     * 隐藏标题栏
     */
    protected open fun setHideBar() {

        immersionBar {
//            hideStatusBar()
            statusBarDarkFont(true)
            hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
            fitsSystemWindows(false)
        }
    }


    /**
     * 显示标题栏 解决布局与状态栏重叠问题 true去重
     */
    protected open fun setShowBar() {
        setShowBar(true)
    }


    /**
     * 显示标题栏
     *
     * @param isFitsSystemWindows 是否去重状态栏 布局与状态栏重叠问题 true去重 false重叠
     */
    protected open fun setShowBar(isFitsSystemWindows: Boolean) {
        immersionBar {
            statusBarDarkFont(true)
            hideBar(BarHide.FLAG_SHOW_BAR)
            fitsSystemWindows(isFitsSystemWindows)
        }
    }


    /**
     * 显示标题栏
     *
     * @param isFitsSystemWindows 是否去重状态栏 布局与状态栏重叠问题 true去重 false重叠
     * @param statusBarColor      设置状态栏颜色
     */
    protected open fun setShowBar(isFitsSystemWindows: Boolean, @ColorRes statusBarColor: Int) {

        immersionBar {
            statusBarDarkFont(true)
            hideBar(BarHide.FLAG_SHOW_BAR)
            fitsSystemWindows(isFitsSystemWindows)
            statusBarColor(statusBarColor)

        }
    }

    /**
     * 设置软键盘冲突(顶起view)
     *
     * @param isKeyboardEnable    是否去顶起View  true是 false不是
     * @param isFitsSystemWindows 是否去重状态栏 布局与状态栏重叠问题 true去重 false重叠
     */
    protected open fun setKeyboardEnable(isKeyboardEnable: Boolean, isFitsSystemWindows: Boolean) {
        immersionBar {
            statusBarDarkFont(true)
            hideBar(BarHide.FLAG_SHOW_BAR)
            fitsSystemWindows(isFitsSystemWindows)
            keyboardEnable(isKeyboardEnable)

        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) immersionBar()
    }

    //======================================================  沉浸式相关 end=========================================================


    override fun onDestroy() {
        super.onDestroy()
        if (isBindEventBus())  EventBusUtil.unregister(this)
    }
}