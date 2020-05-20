package com.xiaoqiang.kotlin.baselibs.http.exception

import com.xiaoqiang.kotlin.baselibs.http.HttpStatus
import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @author 小强
 *
 * @time 2020/5/7  14:10
 *
 * @desc 异常处理
 *
 */
class ExceptionHandle {
    companion object {
        var errorCode = HttpStatus.UNKNOWN_ERROR
        var errorMsg = "请求失败，请稍后重试"

        fun handleException(e: Throwable): String {
            e.printStackTrace()
            if (e is SocketTimeoutException
                || e is ConnectException
                || e is HttpException
            ) { //均视为网络错误
                Logger.e("网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = HttpStatus.NETWORK_ERROR
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {   //均视为解析错误
                Logger.e("数据解析异常: " + e.message)
                errorMsg = "数据解析异常"
                errorCode = HttpStatus.SERVER_ERROR
            } else if (e is ApiException) {//服务器返回的错误信息
                errorMsg = e.message.toString()
                errorCode = HttpStatus.SERVER_ERROR
            } else if (e is UnknownHostException) {
                Logger.e( "网络连接异常: " + e.message)
                errorMsg = "网络连接异常"
                errorCode = HttpStatus.NETWORK_ERROR
            } else if (e is IllegalArgumentException) {
                errorMsg = "参数错误"
                errorCode = HttpStatus.SERVER_ERROR
            } else {//未知错误
                try {
                    Logger.e("错误: " + e.message)
                } catch (e1: Exception) {
                    Logger.e("未知错误Debug调试 ")
                }
                errorMsg = "未知错误，可能抛锚了吧~"
                errorCode = HttpStatus.UNKNOWN_ERROR
            }
            return errorMsg
        }

    }
}