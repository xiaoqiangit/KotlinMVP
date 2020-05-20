package com.xiaoqiang.adapter

import android.provider.SyncStateContract
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xiaoqiang.module_business_card.R
import com.xiaoqiang.module_business_card.bean.MyUserFollowBean

/**
 * @author 小强
 *
 * @time 2019/9/30  17:16
 *
 * @desc 我的关注和粉丝适配器
 *
 */
class MyFollowFansAdapter : BaseQuickAdapter<MyUserFollowBean.Row, BaseViewHolder>(R.layout.business_card_item_user_follow_fans) {

    override fun convert(helper : BaseViewHolder?, item : MyUserFollowBean.Row?) {


        helper?.setText(R.id.tv_nick_name, item?.nickname)

        helper?.setText(R.id.tv_city, item?.city_name ?: "城市")

        helper?.setText(R.id.tv_user_type, "设计师")


        val tvFollow = helper?.getView<TextView>(R.id.tv_follow)
        if (item?.is_follow == true) {

            if (item.is_fans == true) {
                tvFollow?.text = "互粉"
                tvFollow?.isSelected = true

            } else {
                tvFollow?.isSelected = true
                tvFollow?.text = "已关注"
            }

        } else {

            tvFollow?.isSelected = false
            tvFollow?.text = "关注"
        }

        helper?.addOnClickListener(R.id.tv_follow)
    }



}