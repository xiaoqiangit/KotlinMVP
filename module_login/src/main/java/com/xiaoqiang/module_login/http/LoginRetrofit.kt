package com.xiaoqiang.module_login.http

import com.xiaoqiang.kotlin.baselibs.http.RetrofitFactory

/**
 * @author 小强
 *
 * @time 2020/5/11 11:52
 *
 * @desc Retrofit
 *
 */

object LoginRetrofit : RetrofitFactory<LoginApi>() {

    override fun getService(): Class<LoginApi> = LoginApi::class.java

//    override fun baseUrl(): String {
//        return "http://192.168.7.100:9058"
//    }
}