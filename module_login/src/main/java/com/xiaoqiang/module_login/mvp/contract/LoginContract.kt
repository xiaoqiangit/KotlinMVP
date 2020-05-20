package com.xiaoqiang.module_login.mvp.contract

import com.xiaoqiang.kotlin.baselibs.mvp.IModel
import com.xiaoqiang.kotlin.baselibs.mvp.IPresenter
import com.xiaoqiang.kotlin.baselibs.mvp.IView
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.bean.LoginEntity
import io.reactivex.Observable

/**
 * @author 小强
 *
 * @time 2020/5/13  11:23
 *
 * @desc 登录页面契约类
 *
 */
interface LoginContract {

    interface View : IView {
        /**
         * 登录成功
         */
        fun loginSuccess(message:String)
    }


    interface Model : IModel {

        /**
         * 获取登录信息
         */

        fun getLoginData(map: HashMap<String?, Any?>): Observable<HttpResult<LoginEntity>>

    }

    interface Presenter : IPresenter<View> {


        /**
         * 请求登录
         */
        fun requestLogin(map: HashMap<String?, Any?>)

    }

}