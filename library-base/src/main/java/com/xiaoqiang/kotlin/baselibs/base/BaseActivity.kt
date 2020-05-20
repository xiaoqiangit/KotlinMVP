package com.xiaoqiang.kotlin.baselibs.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import com.xiaoqiang.kotlin.baselibs.R
import com.xiaoqiang.library_common.bean.EventBusHelp
import com.xiaoqiang.library_common.utils.CommonUtil
import com.xiaoqiang.library_common.utils.EventBusUtil
import com.xiaoqiang.library_common.utils.KeyBoardUtil
import com.xiaoqiang.library_common.utils.clickUtils.setOnClickDelay
import com.xiaoqiang.library_common.widget.MultipleStatusView
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.base_activity_base_title.*
import kotlinx.android.synthetic.main.base_toolbar.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * @author 小强
 *
 * @time  2020/5/7  17:02
 *
 * @desc Activity基类
 *
 */
abstract class BaseActivity : BaseSwipeBackActivity() {


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // 强制竖屏

        setContentView(R.layout.base_activity_base_title)
        mBaseContainer.addView(layoutInflater.inflate(attachLayoutId(), null))

        mBaseToolbarLeftImg.setOnClickDelay { finish() }

        //初始化沉浸式
        if (isImmersionBarEnabled()) initImmersionBar()

        if (isBindEventBus()) EventBusUtil.register(this)

        initView()
        initData()
        initEvent()
        start()
    }


    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun attachLayoutId(): Int

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    open fun initView() {}

    /**
     * 初始化 事件
     */
    open fun initEvent() {}

    /**
     * 开始请求
     */
    open fun start() {}



    /**
     * 获取权限处理类
     */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }


    //====================================================== EventBus start =========================================================


    /**
     * 是否使用 EventBus
     */
    open fun isBindEventBus(): Boolean = false

    /**
     * 获取 EventBus数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    private fun onEventBus(event: EventBusHelp?) {
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


    //====================================================== toolbar设置 start =========================================================


    /**
     * 获取多态布局
     */
    fun getMultipleStatusView(): MultipleStatusView? = mMultipleStatusView


    /**
     * 设置 toolbar 隐藏
     */
    fun setBaseToolbarGone() {
        mBaseToolbar.visibility = View.GONE
    }


    /**
     * 设置 Title
     */
    fun setBaseTitle(title: String) {
        mBaseToolbarTitle.text = title
    }

    /**
     * 设置 Title
     */
    fun setBaseTitle(@StringRes resId: Int) {
        mBaseToolbarTitle.setText(resId)
    }

    /**
     * 设置 Title 颜色
     */
    fun setBaseTitleColor(@ColorRes color: Int) {
        mBaseToolbarTitle.setTextColor(resources.getColor(color))
    }

    /**
     * 设置 右标题
     */
    fun setBaseToolbarRightText(title: String) {
        mBaseTvToolbarRight.text = title
    }

    /**
     * 设置 右标题
     */
    fun setBaseToolbarRightText(@StringRes resId: Int) {
        mBaseTvToolbarRight.setText(resId)
    }

    /**
     * 设置 右标题 颜色
     */
    fun setBaseToolbarRightTextColor(@ColorRes color: Int) {
        mBaseTvToolbarRight.setTextColor(resources.getColor(color))
        mBaseTvToolbarRight.visibility = View.VISIBLE
    }

    /**
     * 设置 右图标
     */
    fun setBaseToolbarRightImg(@DrawableRes id: Int) {
        mBaseToolbarRightImg.setImageDrawable(resources.getDrawable(id))
        mBaseToolbarRightImg.visibility = View.VISIBLE
    }
    //====================================================== toolbar设置 end =========================================================


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


    //======================================================  沉浸式相关 end=========================================================


    /**
     * 没有焦点的时候就关闭软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            // 如果不是落在EditText区域，则需要关闭输入法
            if (KeyBoardUtil.isHideKeyboard(v, ev)) {
                KeyBoardUtil.hideKeyBoard(this, v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    /**
     * 销毁
     */
    override fun onDestroy() {
        super.onDestroy()
        if (isBindEventBus()) EventBusUtil.unregister(this)
        CommonUtil.fixInputMethodManagerLeak(this)
    }
}