package com.xiaoqiang.kotlin.baselibs.http.constant

/**
 * @author 小强
 *
 * @time 2020/5/7  13:04
 *
 * @desc 请求网络的常量
 *
 */
object HttpConstant {


    const val BASE_URL = "http://192.168.7.100:9028"

    const val DEFAULT_TIMEOUT: Long = 15

    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50 // 50M 的缓存大小

    const val TOKEN_KEY = "token"

    const val SET_COOKIE_KEY = "set-cookie"

    const val COOKIE_NAME = "Cookie"

}