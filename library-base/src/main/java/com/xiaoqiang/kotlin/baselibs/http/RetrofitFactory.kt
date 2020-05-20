package com.xiaoqiang.kotlin.baselibs.http

import com.xiaoqiang.library_common.config.AppConfig
import com.xiaoqiang.kotlin.baselibs.http.constant.HttpConstant
import com.xiaoqiang.kotlin.baselibs.http.interceptor.CacheInterceptor
import com.xiaoqiang.kotlin.baselibs.http.interceptor.CookieInterceptor
import com.xiaoqiang.kotlin.baselibs.http.interceptor.HeaderInterceptor
import com.xiaoqiang.kotlin.baselibs.http.interceptor.LoggerInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author 小强
 *
 * @time 2020/5/7  11:50
 *
 * @desc RetrofitFactory网络请求
 *
 */
abstract class RetrofitFactory<T> {

    var service : T
    private var mBaseUrl = ""
    private var retrofit : Retrofit? = null

    open fun baseUrl() : String = HttpConstant.BASE_URL

    abstract fun getService() : Class<T>

    init {
        mBaseUrl = this.baseUrl()
        if (mBaseUrl.isEmpty()) {
            mBaseUrl = HttpConstant.BASE_URL
            throw RuntimeException("base url can not be empty!")
        }
        service = getRetrofit()!!.create(this.getService())
    }

    /**
     * 获取 Retrofit 实例对象
     */
    private fun getRetrofit() : Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(mBaseUrl)  // baseUrl
                            .client(attachOkHttpClient())
                            //.addConverterFactory(GsonConverterFactory.create())
                            .addConverterFactory(MoshiConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
            }
        }
        return retrofit
    }

    /**
     * 获取 OkHttpClient 实例对象
     * 子类可重写，自定义 OkHttpClient
     */
    open fun attachOkHttpClient() : OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (AppConfig.debug) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        }

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(AppConfig.getApplication().cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(LoggerInterceptor()) //日志,所有的请求响应
            addInterceptor(HeaderInterceptor())
            addInterceptor(CookieInterceptor())
            addInterceptor(CacheInterceptor())
            cache(cache)  //添加缓存
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            //            cookieJar(CookieManager())
        }
        return builder.build()
    }

}