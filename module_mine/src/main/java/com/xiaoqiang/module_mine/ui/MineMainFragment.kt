package com.xiaoqiang.module_mine.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.xiaoqiang.kotlin.baselibs.base.BaseFragment
import com.xiaoqiang.library_common.config.UserConfig
import com.xiaoqiang.library_common.router.RouterActivityPath
import com.xiaoqiang.library_common.router.RouterFragmentPath
import com.xiaoqiang.library_common.utils.ARouterUtil
import com.xiaoqiang.module_mine.R

/**
 * @author 小强
 *
 * @time 2020/5/12  11:40
 *
 * @desc 我的页面
 *
 */

@Route(path = RouterFragmentPath.Mine.PAGER_MINE_MAIN_FRAGMENT)
class MineMainFragment :BaseFragment() {

    /**
     * 加载布局
     */
    override fun attachLayoutId(): Int= R.layout.mine_fragme_main
    /**
     * 懒加载
     */
    override fun lazyLoad() {



    }
}