package com.xiaoqiang.library_common.service

import com.alibaba.android.arouter.facade.template.IProvider
import com.xiaoqiang.library_common.bean.UserInfoEntity

/**
 * @author 小强
 *
 * @time 2020/5/11  15:06
 *
 * @desc APP登陆相关信息
 *
 */
interface ILoginService : IProvider {


    /**
     * 保存登录状态
     *
     * @param status 登录状态
     * @return 返回当前登录状态
     */
    fun saveStatus(status: Boolean): Boolean?

    /**
     * 是否登录
     * @return 是否登录
     */
    fun isLogin(): Boolean

    /**
     * 获取Token
     * @return token
     */
    fun getToken(): String?


    /**
     * 刷新用户信息
     */
    fun refreshUserDetailInfo()

    /**
     * 登录
     */
    fun login()


    /**
     * 保存用户信息
     *
     * @param status 用户信息
     * @return 返回当前用户信息
     */
    fun saveUserInfo(status: String): UserInfoEntity?



    /**
     * 获取用户信息
     * @return 用户信息
     */
    fun getUserInfo(): UserInfoEntity?




}