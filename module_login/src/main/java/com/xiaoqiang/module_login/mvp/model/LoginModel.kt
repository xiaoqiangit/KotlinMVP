package com.xiaoqiang.module_login.mvp.model

import com.xiaoqiang.kotlin.baselibs.mvp.BaseModel
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.bean.LoginEntity
import com.xiaoqiang.module_login.http.LoginRetrofit
import com.xiaoqiang.module_login.mvp.contract.LoginContract
import io.reactivex.Observable

/**
 * @author 小强
 *
 * @time 2020/5/13  14:58
 *
 * @desc 登录Model
 *
 */
class LoginModel : BaseModel(), LoginContract.Model {

    /**
     * 获取登录信息
     */
    override fun getLoginData(map: HashMap<String?, Any?>): Observable<HttpResult<LoginEntity>> =
        LoginRetrofit.service.login(map)

}