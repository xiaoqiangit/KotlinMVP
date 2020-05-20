package com.xiaoqiang.library_common.bean

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * @author 小强
 *
 * @time 2020/5/12  10:15
 *
 * @desc 导航栏 实体类
 *
 */
class
TabEntity : CustomTabEntity {

    var title: String? = ""
    var selectedIcon = 0
    var unSelectedIcon = 0


    constructor(title: String) {
        this.title = title
    }


    constructor(title: String?, selectedIcon: Int, unSelectedIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }

    override fun getTabUnselectedIcon(): Int = unSelectedIcon

    override fun getTabSelectedIcon(): Int = selectedIcon

    override fun getTabTitle(): String = title ?: ""


}