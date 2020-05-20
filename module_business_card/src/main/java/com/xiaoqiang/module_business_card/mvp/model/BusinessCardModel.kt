package com.xiaoqiang.module_business_card.mvp.model

import com.xiaoqiang.kotlin.baselibs.mvp.BaseModel
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean
import com.xiaoqiang.module_business_card.mvp.contract.BusinessCardContract
import com.xiaoqiang.http.BusinessCardRetrofit
import com.xiaoqiang.library_common.bean.HttpResult
import io.reactivex.Observable

/**
 * @author 小强
 *
 * @time 2020/5/14  11:30
 *
 * @desc
 *
 */
class BusinessCardModel :BaseModel(),BusinessCardContract.Model {



    /**
     * 获取关注与粉丝
     */
    override fun getFollowFansData(map : HashMap<String?, Any?>) : Observable<HttpResult<MyUserFollowBean>>  =
            BusinessCardRetrofit.service.getMyUserFollow(map)
}