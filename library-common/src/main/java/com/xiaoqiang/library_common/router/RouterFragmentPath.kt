package com.xiaoqiang.library_common.router

/**
 * @author 小强
 *
 * @time 2020/5/11  12:01
 *
 * @desc Fragment路由地址
 *
 */
object RouterFragmentPath {


    /**
     * 引流页面
     */
    object Drainage {

        private const val MAIN = "/drainage"

        const val PAGER_DRAINAGE_MAIN_FRAGMENT = "$MAIN/main_fragment"

    }


    /**
     * 名片页面
     */
    object BusinessCard {

        private const val MAIN = "/business_card"

        const val PAGER_BUSINESS_CARD_MAIN_FRAGMENT = "$MAIN/main_fragment"

    }

    /**
     * 我的页面
     */
    object Mine {

        private const val MAIN = "/mine"

        const val PAGER_MINE_MAIN_FRAGMENT = "$MAIN/main_fragment"

    }
}