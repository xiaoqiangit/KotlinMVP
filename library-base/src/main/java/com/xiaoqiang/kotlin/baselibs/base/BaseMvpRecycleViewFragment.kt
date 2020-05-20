package com.xiaoqiang.kotlin.baselibs.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.xiaoqiang.kotlin.baselibs.mvp.IPresenter
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.library_common.widget.MultipleStatusView
import kotlinx.android.synthetic.main.base_refresh_layout.*

/**
 * @author 小强
 *
 * @time  2020/5/7  17:02
 *
 * @desc Fragment带刷新基类
 *
 */
abstract class BaseMvpRecycleViewFragment<in V : IView, P : IPresenter<V>> : BaseMvpFragment<V, P>() {


    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView : MultipleStatusView? = null

    /**
     * RecyclerView
     */
    protected var mRecyclerView : RecyclerView? = null


    /**
     * 第一页
     */
    protected var mCurrentPage = 1

    /**
     * 每页数据的个数
     */
    protected var pageSize = 20

    /**
     * 是否是下拉刷新
     */
    protected var isRefresh = true

    /**
     * 初始化 View
     */
    override fun initView(view : View) {
        super.initView(view)
        mLayoutStatusView = mMultipleStatusView
        this.mRecyclerView = RecyclerView
        mSmartRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }
        //多种状态切换的view 重试点击事件
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }


    /**
     * 刷新的监听器(RefreshListener)
     */
    protected val onRefreshListener = OnRefreshListener {
        isRefresh = true
        onRefreshList()
    }

    /**
     * 加载更多的监听器(LoadMoreListener)
     */
    protected val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        isRefresh = false
        mSmartRefreshLayout.finishRefresh() //关闭刷新
        onLoadMoreList()//上拉加载更多
    }

    /**
     * 下拉刷新
     */
    abstract fun onRefreshList()

    /**
     * 上拉加载更多
     */
    abstract fun onLoadMoreList()


    /**
     * 加载
     */
    override fun showLoading() {
    }

    /**
     * 显示加载
     */
    override fun hideLoading() {

        mSmartRefreshLayout.finishRefresh() //关闭刷新

    }


    /**
     * Toast显示错误信息
     *
     * @param errorMsg 错误信息
     */
    override fun showError(errorMsg : String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
    }

    /**
     * 多种状态切换的view 重试点击事件
     */
    open val mRetryClickListener : View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

}