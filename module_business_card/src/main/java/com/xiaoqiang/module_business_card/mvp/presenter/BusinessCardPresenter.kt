package com.xiaoqiang.module_business_card.mvp.presenter

import com.xiaoqiang.kotlin.baselibs.ext.requestCache
import com.xiaoqiang.kotlin.baselibs.mvp.BasePresenter
import com.xiaoqiang.library_common.bean.HttpResult
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean
import com.xiaoqiang.module_business_card.mvp.contract.BusinessCardContract
import com.xiaoqiang.module_business_card.mvp.model.BusinessCardModel
import com.zchu.rxcache.RxCache
import com.zchu.rxcache.kotlin.load
import io.reactivex.Observable


/**
 * @author 小强
 *
 * @time 2020/5/14  11:30
 *
 * @desc
 *
 */
class BusinessCardPresenter : BasePresenter<BusinessCardContract.Model, BusinessCardContract.View>(), BusinessCardContract.Presenter {


    /**
     * 创建 Model
     */
    override fun createModel() : BusinessCardContract.Model? {
        return BusinessCardModel()
    }

    /**
     * 请求关注与粉丝
     */
    override fun requestFollowFans(map : HashMap<String?, Any?>) {

        val followFansData : Observable<HttpResult<MyUserFollowBean>>? = mModel?.getFollowFansData(map)


                followFansData?.requestCache(mModel, mView, false, onSuccess = {
                    it.data?.let { it1 -> mView?.getFollowFansSuccess(it1) }
                }, onCache = {

                    RxCache.getDefault()
                            .load<MyUserFollowBean>("custom_key")
                            .subscribe {
                                mView?.getFollowFansSuccess(it.data)
                            }
                })


    }
}










