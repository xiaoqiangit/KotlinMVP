package com.xiaoqiang.module_login.mvp.presenter

import com.xiaoqiang.kotlin.baselibs.ext.requestModel
import com.xiaoqiang.kotlin.baselibs.http.constant.HttpConstant
import com.xiaoqiang.kotlin.baselibs.mvp.BasePresenter
import com.xiaoqiang.library_common.constants.PreferenceKey
import com.xiaoqiang.library_common.utils.PreferenceUtil
import com.xiaoqiang.module_login.mvp.contract.LoginContract
import com.xiaoqiang.module_login.mvp.model.LoginModel

/**
 * @author 小强
 *
 * @time 2020/5/13  15:05
 *
 * @desc 登录Presenter
 *
 */
class LoginPresenter : BasePresenter<LoginContract.Model, LoginContract.View>(),
    LoginContract.Presenter {


    /**
     * 创建 Model
     */
    override fun createModel(): LoginContract.Model? = LoginModel()

    /**
     * 请求登录
     */
    override fun requestLogin(map: HashMap<String?, Any?>) {

        mModel?.getLoginData(map)?.requestModel(mModel, mView, onSuccess = {

            var toke: String by PreferenceUtil(HttpConstant.TOKEN_KEY, "")
            toke = it.data.token ?: ""

            mView?.loginSuccess(it.message)
        })
    }

}