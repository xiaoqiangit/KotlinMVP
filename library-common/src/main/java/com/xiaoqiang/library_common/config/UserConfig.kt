package com.xiaoqiang.library_common.config

import com.orhanobut.logger.Logger
import com.xiaoqiang.library_common.bean.BaseEntity
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.library_common.bean.LoginEntity
import com.xiaoqiang.library_common.constants.PreferenceKey
import com.xiaoqiang.library_common.utils.GsonUtil
import com.xiaoqiang.library_common.utils.PreferenceUtil

/**
 * @author 小强
 *
 * @time 2020/5/11  14:48
 *
 * @desc 用户登录信息
 *
 */
object UserConfig {


    /**
     * 判断是否登录
     * @return true:已登录 false:未登录
     */
    fun isLogin(): Boolean {
        val loginMessage: String by PreferenceUtil(PreferenceKey.LOGIN_MESSAGE_KEY, "")

        if (loginMessage.isNullOrEmpty()) {
            return false
        }
        return true
    }

    /**
     * 判断是否登录
     * @return true:已登录 false:未登录
     */
    fun getResult(): LoginEntity? {
        val loginMessage: String by PreferenceUtil(PreferenceKey.LOGIN_MESSAGE_KEY, "")

        if (loginMessage.isNullOrEmpty()) {
            return null
        }

        val loginEntity = GsonUtil.fromJson(loginMessage, LoginEntity::class.java)


        Logger.d("loginMessage---->:$loginMessage")
        Logger.d("---->:$loginEntity")
        return loginEntity
    }

    /**
     * 保存用户信息
     */
    fun setUserConfig(result: HttpResult<LoginEntity>?) {

        val toJsonString = GsonUtil.toJson(result)

        var logMessage: String by PreferenceUtil(
            PreferenceKey.LOGIN_MESSAGE_KEY,
            toJsonString ?: ""
        )
        logMessage = toJsonString ?: ""


        var token: String by PreferenceUtil(
            PreferenceKey.TOKEN,
            toJsonString ?: ""
        )
        token = result?.data?.token ?: ""


    }

}