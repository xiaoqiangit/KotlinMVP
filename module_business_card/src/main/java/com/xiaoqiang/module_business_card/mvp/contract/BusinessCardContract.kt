package com.xiaoqiang.module_business_card.mvp.contract

import com.xiaoqiang.kotlin.baselibs.mvp.IModel
import com.xiaoqiang.kotlin.baselibs.mvp.IPresenter
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean
import io.reactivex.Observable

/**
 * @author 小强
 *
 * @time 2020/5/14  11:30
 *
 * @desc
 *
 */
interface BusinessCardContract {

    interface View : IView {

        /**
         * 获取到的关注与粉丝
         */
        fun getFollowFansSuccess(result : MyUserFollowBean)
    }


    interface Model : IModel {

        /**
         * 获取关注与粉丝
         */

        fun getFollowFansData(map : HashMap<String?, Any?>) : Observable<HttpResult<MyUserFollowBean>>
    }


    interface Presenter : IPresenter<View> {
        /**
         * 请求关注与粉丝
         */
        fun requestFollowFans(map : HashMap<String?, Any?>)
    }

}