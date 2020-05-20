package com.xiaoqiang.library_common.adapter

import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author 小强
 *
 * @time 2020/5/12  10:44
 *
 * @desc 主页导航栏适配器
 *
 */
class MyPageAdapter : FragmentPagerAdapter {

    private var fragments: MutableList<Fragment>? = null


    constructor(@NonNull fm: FragmentManager?) : super(fm)

    fun setData(data: List<Fragment>?) {

        fragments= data as MutableList<Fragment>?

        notifyDataSetChanged()
    }

    @NonNull
    override fun getItem(position: Int): Fragment? {
        return fragments?.get(position)
    }

    override fun getCount(): Int {

        return fragments?.size ?: 0
    }
}