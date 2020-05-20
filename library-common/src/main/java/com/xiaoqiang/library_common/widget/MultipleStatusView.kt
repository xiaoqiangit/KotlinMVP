package com.xiaoqiang.library_common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.xiaoqiang.library_common.R
import java.util.*

/**
 * @author 小强
 *
 * @time 2020/5/9  9:59
 *
 * @desc 状态页
 *
 */
class MultipleStatusView : RelativeLayout {


    companion object {
        private val DEFAULT_LAYOUT_PARAMS = LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        )

        private const val STATUS_CONTENT = 0x00
        private const val STATUS_LOADING = 0x01
        private const val STATUS_EMPTY = 0x02
        private const val STATUS_ERROR = 0x03
        private const val STATUS_NO_NETWORK = 0x04
        private const val NULL_RESOURCE_ID = -1
    }

    private var mEmptyView : View? = null
    private var mErrorView : View? = null
    private var mLoadingView : View? = null
    private var mNoNetworkView : View? = null
    private var mContentView : View? = null
    private var mEmptyViewResId = 0
    private var mErrorViewResId = 0
    private var mLoadingViewResId = 0
    private var mNoNetworkViewResId = 0
    private var mContentViewResId = 0

    private var mViewStatus = 0
    private var mInflater : LayoutInflater? = null
    private var mOnRetryClickListener : OnClickListener? = null

    private val mOtherIds : ArrayList<Int>? = ArrayList()

    constructor(context : Context) : this(context, null)

    constructor(context : Context, attrs : AttributeSet?) : this(context, attrs, 0)

    constructor(context : Context, attrs : AttributeSet?, defStyleAttr : Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CommonMultipleStatusView, defStyleAttr, 0)
        mEmptyViewResId = a.getResourceId(R.styleable.CommonMultipleStatusView_CommonEmptyView, R.layout.common_layout_empty_view)
        mErrorViewResId = a.getResourceId(R.styleable.CommonMultipleStatusView_CommonErrorView, R.layout.common_layout_error_view)
        mLoadingViewResId = a.getResourceId(R.styleable.CommonMultipleStatusView_CommonLoadingView, R.layout.common_layout_loading_view)
        mNoNetworkViewResId = a.getResourceId(R.styleable.CommonMultipleStatusView_CommonNoNetworkView, R.layout.common_layout_network_view)
        mContentViewResId = a.getResourceId(R.styleable.CommonMultipleStatusView_CommonContentView, NULL_RESOURCE_ID)
        a.recycle()
        mInflater = LayoutInflater.from(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        showContent()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear(mEmptyView, mLoadingView, mErrorView, mNoNetworkView)
        mOtherIds?.clear()
        if (null != mOnRetryClickListener) {
            mOnRetryClickListener = null
        }
        mInflater = null
    }

    /**
     * 获取当前状态
     */
    fun getViewStatus() : Int {
        return mViewStatus
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    fun setOnRetryClickListener(onRetryClickListener : OnClickListener?) {
        mOnRetryClickListener = onRetryClickListener
    }

    /**
     * 显示空视图
     */
    fun showEmpty() {
        showEmpty(mEmptyViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示空视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showEmpty(layoutId : Int, layoutParams : ViewGroup.LayoutParams?) {
        showEmpty(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示空视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showEmpty(view : View?, layoutParams : ViewGroup.LayoutParams?) {
        checkNull(view, "Empty view is null!")
        mViewStatus = STATUS_EMPTY
        if (null == mEmptyView) {
            mEmptyView = view
            val emptyRetryView = mEmptyView?.findViewById<View>(R.id.mEmptyView)
            if (null != mOnRetryClickListener && null != emptyRetryView) {
                emptyRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds?.add(mEmptyView!!.id)
            addView(mEmptyView, 0, layoutParams)
        }
        showViewById(mEmptyView!!.id)
    }

    /**
     * 显示错误视图
     */
    fun showError() {
        showError(mErrorViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示错误视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showError(layoutId : Int, layoutParams : ViewGroup.LayoutParams?) {
        showError(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示错误视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showError(view : View?, layoutParams : ViewGroup.LayoutParams?) {
        checkNull(view, "Error view is null!")
        mViewStatus = STATUS_ERROR
        if (null == mErrorView) {
            mErrorView = view
            val errorRetryView =
                    mErrorView!!.findViewById<View>(R.id.error_view)
            if (null != mOnRetryClickListener && null != errorRetryView) {
                errorRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds?.add(mErrorView!!.id)
            addView(mErrorView, 0, layoutParams)
        }
        showViewById(mErrorView!!.id)
    }

    /**
     * 显示加载中视图
     */
    fun showLoading() {
        showLoading(mLoadingViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示加载中视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showLoading(layoutId : Int, layoutParams : ViewGroup.LayoutParams?) {
        showLoading(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示加载中视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showLoading(view : View?, layoutParams : ViewGroup.LayoutParams?) {
        checkNull(view, "Loading view is null!")
        mViewStatus = STATUS_LOADING
        if (null == mLoadingView) {
            mLoadingView = view
            mOtherIds?.add(mLoadingView!!.id)
            addView(mLoadingView, 0, layoutParams)
        }
        showViewById(mLoadingView!!.id)
    }

    /**
     * 显示无网络视图
     */
    fun showNoNetwork() {
        showNoNetwork(mNoNetworkViewResId, DEFAULT_LAYOUT_PARAMS)
    }

    /**
     * 显示无网络视图
     * @param layoutId 自定义布局文件
     * @param layoutParams 布局参数
     */
    fun showNoNetwork(layoutId : Int, layoutParams : ViewGroup.LayoutParams?) {
        showNoNetwork(inflateView(layoutId), layoutParams)
    }

    /**
     * 显示无网络视图
     * @param view 自定义视图
     * @param layoutParams 布局参数
     */
    fun showNoNetwork(
            view : View?,
            layoutParams : ViewGroup.LayoutParams?
    ) {
        checkNull(view, "No network view is null!")
        mViewStatus = STATUS_NO_NETWORK
        if (null == mNoNetworkView) {
            mNoNetworkView = view
            val noNetworkRetryView =
                    mNoNetworkView!!.findViewById<View>(R.id.no_network_view)
            if (null != mOnRetryClickListener && null != noNetworkRetryView) {
                noNetworkRetryView.setOnClickListener(mOnRetryClickListener)
            }
            mOtherIds?.add(mNoNetworkView!!.id)
            addView(mNoNetworkView, 0, layoutParams)
        }
        showViewById(mNoNetworkView!!.id)
    }

    /**
     * 显示内容视图
     */
    fun showContent() {
        mViewStatus = STATUS_CONTENT
        if (null == mContentView && mContentViewResId != NULL_RESOURCE_ID) {
            mContentView = mInflater!!.inflate(mContentViewResId, null)
            addView(mContentView, 0, DEFAULT_LAYOUT_PARAMS)
        }
        showContentView()
    }

    private fun showContentView() {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (mOtherIds?.contains(view.id)!!) View.GONE else View.VISIBLE
        }
    }

    private fun inflateView(layoutId : Int) : View? {
        return mInflater!!.inflate(layoutId, null)
    }

    private fun showViewById(viewId : Int) {
        val childCount = childCount
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view.visibility = if (view.id == viewId) View.VISIBLE else View.GONE
        }
    }

    private fun checkNull(`object` : Any?, hint : String) {
        if (null == `object`) {
            throw NullPointerException(hint)
        }
    }

    private fun clear(vararg views : View?) {

        if (views.isNullOrEmpty()) {
            return
        }
        try {

            for (view in views) {

                if (views.isNullOrEmpty()) {
                    return
                }

                view?.let { removeView(it) }
            }
        } catch (e : java.lang.Exception) {
            e.printStackTrace()
        }


    }
}