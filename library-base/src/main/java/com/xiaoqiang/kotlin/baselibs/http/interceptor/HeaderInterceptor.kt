package com.xiaoqiang.kotlin.baselibs.http.interceptor

import com.orhanobut.logger.Logger
import com.xiaoqiang.kotlin.baselibs.http.constant.HttpConstant
import com.xiaoqiang.library_common.utils.PreferenceUtil
import okhttp3.Interceptor
import okhttp3.Response


/**
 * @author 小强
 *
 * @time 2020/5/7  14:10
 *
 * @desc 设置请求头
 *
 */
class HeaderInterceptor : Interceptor {

    //token
    private var token: String by PreferenceUtil(HttpConstant.TOKEN_KEY, "")

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Token", token)
        builder.addHeader("Content-type", "application/json; charset=utf-8")
        // .method(request.method(), request.body())

        Logger.v("Token---->:$token" )
        val domain = request.url().host()
        val url = request.url().toString()
        if (domain.isNotEmpty()) {
            val spDomain: String by PreferenceUtil(domain, "")
            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }

        return chain.proceed(builder.build())
    }

}