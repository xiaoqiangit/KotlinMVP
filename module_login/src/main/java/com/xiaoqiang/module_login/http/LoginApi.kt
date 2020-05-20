package com.xiaoqiang.module_login.http

import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.bean.LoginEntity
import io.reactivex.Observable
import retrofit2.http.*


/**
 * @author 小强
 *
 * @time 2020/5/11 11:52
 *
 * @desc 请求
 *
 */
interface LoginApi {


    /**
     * 登录
     */
    @POST("/api/user/login")
    @FormUrlEncoded
    fun login(@FieldMap map: HashMap<String?, Any?>): Observable<HttpResult<LoginEntity>>




}