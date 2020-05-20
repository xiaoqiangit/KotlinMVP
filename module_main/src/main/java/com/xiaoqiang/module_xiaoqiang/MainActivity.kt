package com.xiaoqiang.module_xiaoqiang

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import com.alibaba.android.arouter.facade.annotation.Route
import com.xiaoqiang.kotlin.baselibs.base.BaseActivity
import com.xiaoqiang.library_common.router.RouterActivityPath
import com.xiaoqiang.library_common.router.RouterFragmentPath
import com.xiaoqiang.library_common.utils.ARouterUtil
import com.xiaoqiang.module_main.R
import com.xiaoqiang.library_common.adapter.MyPageAdapter
import com.xiaoqiang.library_common.config.UserConfig
import kotlinx.android.synthetic.main.xiao_qiang_activity_main.*
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

/**
 * @author 小强
 *
 * @time 2020/5/7  16:54
 *
 * @desc 主页
 *
 */
@Route(path = RouterActivityPath.Main.PAGER_MAIN)
class MainActivity : BaseActivity() {


    private var mNavigationController: NavigationController? = null

    private val mFragmentsList: MutableList<Fragment>? = arrayListOf()


//    private val mDialog by lazy {
//        DialogUtil.getWaitDialog(this, "正在加载")
//    }
//
//    override fun showLoading() {
//        mDialog.show()
//    }
//
//    override fun hideLoading() {
//        mDialog.dismiss()
//    }

    override fun attachLayoutId(): Int = R.layout.xiao_qiang_activity_main


    override fun initView() {
        super.initView()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setBaseToolbarGone()

        setSwipeBackEnable(false)


        mNavigationController = mPageNavigationView.material()
            .addItem(
                R.mipmap.main_icon_drainage_normal,
                R.mipmap.main_icon_drainage_select,
                resources.getString(R.string.main_drainage),
                resources.getColor(R.color.common_color_333333)
            )
            .addItem(
                R.mipmap.main_icon_business_card_normal,
                R.mipmap.main_icon_business_card_select,
                resources.getString(R.string.main_business_card),
                resources.getColor(R.color.common_color_333333)
            )
            .addItem(
                R.mipmap.main_icon_my_normal,
                R.mipmap.main_icon_my_select,
                resources.getString(R.string.main_my),
                resources.getColor(R.color.common_color_333333)
            )
            .enableAnimateLayoutChanges()
            .build()


        val mainPageAdapter =
            MyPageAdapter(
                supportFragmentManager
            )
        mCuContentView.adapter = mainPageAdapter

        mCuContentView.offscreenPageLimit = 3
        mNavigationController?.setupWithViewPager(mCuContentView)
        initFragment(mainPageAdapter)

    }

    /**
     * 通过ARouter 获取其他组件提供的fragment
     */
    private fun initFragment(mainPageAdapter: MyPageAdapter) {


        val drainageFragment =
            ARouterUtil.getARouter(RouterFragmentPath.Drainage.PAGER_DRAINAGE_MAIN_FRAGMENT) as Fragment

        val businessCardFragment =
            ARouterUtil.getARouter(RouterFragmentPath.BusinessCard.PAGER_BUSINESS_CARD_MAIN_FRAGMENT) as Fragment

        val mineFragment =
            ARouterUtil.getARouter(RouterFragmentPath.Mine.PAGER_MINE_MAIN_FRAGMENT) as Fragment


        mFragmentsList?.add(drainageFragment)
        mFragmentsList?.add(businessCardFragment)
        mFragmentsList?.add(mineFragment)

        mainPageAdapter.setData(mFragmentsList)
    }

    /**
     * 初始化 事件
     */
    override fun initEvent() {

        //点击事件
        mNavigationController?.addTabItemSelectedListener(object : OnTabItemSelectedListener {

            override fun onSelected(index: Int, old: Int) {

                if (index == 2) {
                    if (!UserConfig.isLogin()) {
                        ARouterUtil.getARouter(RouterActivityPath.Login.PAGER_LOGIN)
                    }
                }
            }

            override fun onRepeat(index: Int) {
            }
        })
    }
}
