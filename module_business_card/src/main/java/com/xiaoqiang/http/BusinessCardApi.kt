package com.xiaoqiang.http

import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean
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
interface BusinessCardApi {


    /**
     * 登录
     */
    @POST("/api/userfollow/getuserlist")
    @FormUrlEncoded
    fun getMyUserFollow(@FieldMap map: HashMap<String?, Any?>): Observable<HttpResult<MyUserFollowBean>>




}