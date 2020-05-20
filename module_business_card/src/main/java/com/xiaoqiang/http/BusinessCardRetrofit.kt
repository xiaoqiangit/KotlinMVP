package com.xiaoqiang.http

import com.xiaoqiang.kotlin.baselibs.http.RetrofitFactory

/**
 * @author 小强
 *
 * @time 2020/5/11 11:52
 *
 * @desc Retrofit
 *
 */

object BusinessCardRetrofit : RetrofitFactory<BusinessCardApi>() {

    override fun getService(): Class<BusinessCardApi> = BusinessCardApi::class.java

}