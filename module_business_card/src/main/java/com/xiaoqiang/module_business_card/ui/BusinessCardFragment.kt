package com.xiaoqiang.module_business_card.ui

import android.annotation.SuppressLint
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.orhanobut.logger.Logger
import com.xiaoqiang.adapter.MyFollowFansAdapter
import com.xiaoqiang.kotlin.baselibs.base.BaseLayoutManager
import com.xiaoqiang.kotlin.baselibs.base.BaseMvpRecycleViewFragment
import com.xiaoqiang.library_common.router.RouterFragmentPath
import com.xiaoqiang.module_business_card.R
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean
import com.xiaoqiang.module_business_card.mvp.contract.BusinessCardContract
import com.xiaoqiang.module_business_card.mvp.presenter.BusinessCardPresenter
import com.zchu.rxcache.RxCache
import com.zchu.rxcache.diskconverter.GsonDiskConverter
import com.zchu.rxcache.kotlin.load

import java.io.File

/**
 * @author 小强
 *
 * @time 2020/5/12  11:44
 *
 * @desc 名片页面
 *
 */
@Route(path = RouterFragmentPath.BusinessCard.PAGER_BUSINESS_CARD_MAIN_FRAGMENT)
class BusinessCardFragment :
        BaseMvpRecycleViewFragment<BusinessCardContract.View, BusinessCardContract.Presenter>(),
        BusinessCardContract.View {


    private val mAdapter : MyFollowFansAdapter by lazy {
        MyFollowFansAdapter()
    }


    /**
     * 创建当前Presenter
     */
    override fun createPresenter() : BusinessCardContract.Presenter = BusinessCardPresenter()

    /**
     * 加载布局
     */
    override fun attachLayoutId() : Int = R.layout.business_card_fragment_main

    /**
     * 懒加载
     */
    @SuppressLint("CheckResult")
    override fun lazyLoad() {
        RxCache.initializeDefault(RxCache.Builder()
                .appVersion(1)
                .diskDir(File(context?.cacheDir?.path + File.separator + "data-cache"))
                .diskConverter(GsonDiskConverter())
                .diskMax((20 * 1024 * 1024).toLong())
                .memoryMax(0)
                .setDebug(true)
                .build())


        mLayoutStatusView?.showLoading()

        val map = hashMapOf<String?, Any?>()
        map["PageIndex"] = mCurrentPage
        map["PageCount"] = pageSize
        mPresenter?.requestFollowFans(map)


    }

    /**
     * 初始化 View
     */
    override fun initView(view : View) {
        super.initView(view)
        mRecyclerView?.adapter = mAdapter

        mAdapter.run {
            bindToRecyclerView(mRecyclerView)
            setOnLoadMoreListener(onRequestLoadMoreListener, mRecyclerView)
            BaseLayoutManager.Builder(context, mRecyclerView)
                    .setSpace(20f)
                    .setLayoutManager(BaseLayoutManager.LINEAR_LAYOUT).build()
        }

    }

    /**
     * 获取到的关注与粉丝
     */
    override fun getFollowFansSuccess(result : MyUserFollowBean) {

        RxCache.getDefault().save("custom_key", result)?.subscribe()

        result.let {
            Logger.d("---->:$")
            mAdapter.run {
                if (isRefresh) {
                    it.rows?.let { it1 -> replaceData(it1) }
                } else {
                    it.rows?.let { it1 -> addData(it1) }
                }
                //                pageSize = result.total ?: 0
                Logger.d("---->:$")

                Logger.d("---->:${mAdapter.data.size}")

                if (mAdapter.data.size == it.total) {
                    loadMoreEnd(isRefresh)
                } else {
                    loadMoreComplete()
                }
            }
        }
        if (mAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }


    /**
     * 加载
     */
    override fun showLoading() {
        super.showLoading()

    }


    /**
     * 隐藏加载
     */
    override fun hideLoading() {
        super.hideLoading()
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        }
    }

    /**
     * Toast显示默认的提示(如:网络错误,token失效等)
     * @param msg: 默认信息
     */
    @SuppressLint("CheckResult")
    override fun showDefaultMsg(msg : String) {
        super.showDefaultMsg(msg)

        mAdapter.run {
            if (data.size > 0) {
                mAdapter.loadMoreFail()

            } else {

                mLayoutStatusView?.showNoNetwork()
            }
        }
    }


    /**
     * Toast显示错误信息
     *
     * @param errorMsg 错误信息
     */
    override fun showError(errorMsg : String) {
        super.showError(errorMsg)
        if (isRefresh) {
            mAdapter.setEnableLoadMore(true)
        } else {
            mAdapter.loadMoreFail()
        }
    }

    /**
     * 下拉刷新
     */
    override fun onRefreshList() {
        mAdapter.setEnableLoadMore(false)
        val map = hashMapOf<String?, Any?>()
        map["PageIndex"] = mCurrentPage
        map["PageCount"] = pageSize
        mPresenter?.requestFollowFans(map)
    }

    /**
     * 上拉加载更多
     */
    override fun onLoadMoreList() {

        Logger.d("mCurrentPage---->:$mCurrentPage")

        val page = mAdapter.data.size / pageSize + mCurrentPage

        val map = hashMapOf<String?, Any?>()
        map["PageIndex"] = page
        map["PageCount"] = pageSize

        mPresenter?.requestFollowFans(map)
    }


}