package com.xiaoqiang.module_xiaoqiang

import com.alibaba.android.arouter.facade.annotation.Route
import com.xiaoqiang.kotlin.baselibs.base.BaseFragment
import com.xiaoqiang.module_main.R

/**
 * @author 小强
 *
 * @time 2020/5/9  14:00
 *
 * @desc
 *
 */
@Route(path = "/news/XiaoqaingFragment")
class XiaoqaingFragment : BaseFragment() {

    override fun attachLayoutId(): Int = R.layout.xiao_qiang_activity_main
    override fun lazyLoad() {

    }


}