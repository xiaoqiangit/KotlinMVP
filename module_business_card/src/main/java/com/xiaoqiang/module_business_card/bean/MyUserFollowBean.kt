package com.xiaoqiang.module_business_card.bean


/**
 * @author 小强
 *
 * @time 2020/5/14  11:34
 *
 * @desc
 *
 */
data class MyUserFollowBean(
        val rows : List<Row?>? = listOf(),
        val total : Int? = 0 // 26
) {

    data class Row(
            val city_name : String? = "", // 小强
            val create_datetime : String? = "", // 2019-05-30 14:28:46
            val fans_count : Int? = 0, // 1
            val follow_count : Int? = 0, // 1
            val follow_groups : List<Any?>? = listOf(),
            val follow_id : Int? = 0, // 261
            val follow_remark : String? = "", // 备注名
            val follow_user_id : String? = "", // 87589
            val user_id : String? = "", // 87589
            val head_img : String? = "", // http://192.168.7.100:8028/files/img/20180905/201809051141540853.jpg
            val introduction : String? = "", //这是个人介绍
            val is_fans : Boolean? = false, // false
            var is_follow : Boolean? = false, // true
            val nickname : String? = "", // 小强
            val province_name : String? = "", // 小强
            val update_datetime : String? = "", // 2019-05-30 14:28:46
            val user_type : Int? = 0 // 2
    )
}


