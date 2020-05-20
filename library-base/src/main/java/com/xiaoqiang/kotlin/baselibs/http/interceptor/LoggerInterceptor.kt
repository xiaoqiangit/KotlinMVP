package com.xiaoqiang.kotlin.baselibs.http.interceptor

import android.text.TextUtils
import com.xiaoqiang.library_common.config.AppConfig
import com.orhanobut.logger.Logger
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 * @author 小强
 *
 * @time 2020/5/10  20:15
 *
 * @desc 打印日志
 *
 */
class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder().build()

        printRequestLog(originalRequest)
        var response: Response? = null
        try {
            //发送网络请求
            response = chain.proceed(originalRequest)
            printResult(response)
        } catch (e: SocketTimeoutException) {
            //此处不抛异常  连接超时会crash 没有找到其他好的方法
            e.printStackTrace()
        }
        return response!!
    }

    /**
     * 打印请求日志
     */
    @Throws(IOException::class)
    private fun printRequestLog(originalRequest: Request) {
        val formBuilder = FormBody.Builder()
        var msg = "${originalRequest.url()}\n"

        val oidBody = originalRequest.body()
        if (oidBody is FormBody) {
            val formBody = oidBody
            for (i in 0 until formBody.size()) {
                val name =
                    URLDecoder.decode(formBody.encodedName(i), "utf-8")
                val value =
                    URLDecoder.decode(formBody.encodedValue(i), "utf-8")
                if (!TextUtils.isEmpty(value)) {
                    formBuilder.add(name, value)
                    msg += "$name  =  $value\n"
                }
            }
        }
        if (AppConfig.debug) {
            Logger.v(msg)
        }

    }

    /**
     * 打印返回日志
     */
    @Throws(IOException::class)
    private fun printResult(response: Response) {
        val responseBody = response.body()
        val source = responseBody!!.source()
        source.request(Long.MAX_VALUE)
        val buffer = source.buffer()
        var UTF8 = Charset.forName("UTF-8")
        val contentType = responseBody.contentType()
        if (contentType != null) {
            UTF8 = contentType.charset(UTF8)
        }
        val json = buffer.clone().readString(UTF8)

        if (AppConfig.debug) {
            Logger.v(json)
        }

    }
}