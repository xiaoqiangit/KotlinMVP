package com.xiaoqiang.library_common.bean

/**
 * @author 小强
 *
 * @time 2020/5/7  16:54
 *
 * @desc 登录实体类
 *
 */
data class LoginEntity(
    val channelList: Any? = Any(), // null
    val editorExperienceServiceApply: EditorExperienceServiceApply? = EditorExperienceServiceApply(),
    val encrypt_code: String? = "", // NuBtlFfHrPFArMSVnl/FuFn1jpTjPwbQbm6GJ+c3Lfo=
    val id: Int? = 0, // 17
    val im_password: String? = "", // c64c6c47898c9b17251f89b4fd605ee7
    val im_username: String? = "", // jydbr9
    val is_bind_wechat: Boolean? = false, // true
    val is_vip: Boolean? = false, // true
    val last_login_datetime: String? = "", // 2020-05-10 19:54:43
    val locked: Boolean? = false, // false
    val login_count: Int? = 0, // 1626
    val name: String? = "", // 无极
    val other_info: OtherInfo? = OtherInfo(),
    val password_md5: String? = "", // 25d55ad283aa400af464c76d713c07ad
    val phone: String? = "", // 18822223335
    val phone_country_code: String? = "", // 86
    val role: List<Role?>? = listOf(),
    val service: List<Service?>? = listOf(),
    val service_packages: List<ServicePackage?>? = listOf(),
    val token: String? = "", // v3jEQ4alPciYA7b/gchSKbluXamXdig5s4eYAcfAGFs47epVCzLStrUqC3BhYujCPzmjXHC2ly578na3X8q9LfzbMHzK6ibvOjnGv8r8EQIWuASA0Ab2OagfvtvAID+W63GYPsiP1gPZNjNS6wcyqR3zZ41rfDnHcTBJhnLUGmdh2j/6UmNLctKrU+KDmiu5M2ot2J9XUbXLZJVoaKuajKzoXBh4rfsqQbMgq/bmtrDjlHWn4Uws9HOAniE6+IrVXe4wmMjb7FHHObZKpY8S5jpLwLNYc4uRVTBw/0kKmSufOlpoHFZCbWQjabN51GRIQWM0xCrRJLjg0p3N5V0VyA==
    val user_info: UserInfo? = UserInfo(),
    val user_operation: UserOperation? = UserOperation(),
    val user_type: Int? = 0, // 2
    val username: String? = "", // 18822223335
    val wx_user_info: WxUserInfo? = WxUserInfo()
) {
    data class EditorExperienceServiceApply(
        val agent_id: Int? = 0, // 0
        val agent_user_id: Int? = 0, // 89014
        val area_code: String? = "", // 440106
        val auth_email: Any? = Any(), // null
        val auth_idcard: Any? = Any(), // null
        val auth_mobile: Any? = Any(), // null
        val auth_name: Any? = Any(), // null
        val auth_phone: Any? = Any(), // null
        val auth_position: Any? = Any(), // null
        val bank_id: Int? = 0, // 0
        val check_status: Int? = 0, // 1
        val city_code: String? = "", // 440100
        val company: String? = "", // 广州精细涂料有限公司
        val contract_amount: Any? = Any(), // null
        val contract_attached: Any? = Any(), // null
        val contract_no: Any? = Any(), // null
        val create_datetime: String? = "", // 2019-07-23 15:12:12
        val id: Int? = 0, // 16
        val package_name: String? = "", // Editor.experience
        val phone: String? = "", // 18822223335
        val province_code: String? = "", // 440000
        val step_index: Int? = 0, // 0
        val subsidy_red_packet: Int? = 0, // 0
        val trade: String? = "", // 133,142
        val update_datetime: String? = "", // 2019-11-21 13:53:18
        val user_id: Int? = 0, // 17
        val username: String? = "" // 京城
    )

    data class OtherInfo(
        val authorization_letter: String? = "", // http://192.168.0.100:9028/files/file/20191126/201911261151129869.docx
        val certificates_type: Int? = 0, // 2
        val certificates_url: String? = "", // http://192.168.0.100:9028/files/img/20191015/201910151727124484.png
        val check_status: Int? = 0, // 2
        val city: String? = "", // 420100
        val city_name: Any? = Any(), // null
        val create_datetime: String? = "", // 2018-01-13 11:45:33
        val credit_code: String? = "", // itop899998888811
        val end_datetime: String? = "", // 2020-10-28 00:00:00
        val id: Int? = 0, // 9
        val message: String? = "",
        val name: String? = "", // iTOP创意秀
        val profession_id: Int? = 0, // 0
        val profession_name: String? = "", // CEO
        val province: String? = "", // 420000
        val province_name: Any? = Any(), // null
        val remark: Any? = Any(), // null
        val reply_datetime: String? = "", // 1900-01-01 00:00:00
        val scale: String? = "", // 100-499人
        val short_name: String? = "", // 小强无限公司3
        val start_datetime: String? = "", // 2019-10-28 00:00:00
        val trade: String? = "", // 133,143
        val trade_Name: Any? = Any(), // null
        val update_datetime: String? = "", // 2019-12-18 14:52:13
        val user_id: Int? = 0, // 17
        val version: Int? = 0 // 3
    )

    data class Role(
        val actionType: String? = "", // Mobile.flashScreen
        val actionValue: String? = "", // 28
        val id: Int? = 0, // 0
        val roleId: Int? = 0 // 3
    )

    data class Service(
        val endDate: String? = "", // 2020-10-28 00:00:00
        val name: String? = "", // Editor.actionStatus
        val number: Int? = 0, // 0
        val startDate: String? = "" // 2019-10-28 00:00:00
    )

    data class ServicePackage(
        val endDate: String? = "", // 2020-10-28 00:00:00
        val name: String? = "", // 自营销旗舰版
        val startDate: String? = "" // 2019-10-28 00:00:00
    )

    data class UserInfo(
        val age: Int? = 0, // 99
        val alipay_account: String? = "", // 163@163.com
        val area: Any? = Any(), // null
        val birth_year: String? = "", // 90后
        val birthday_day: Int? = 0, // 25
        val birthday_month: Int? = 0, // 2
        val career: String? = "", // 码农
        val certification_result: String? = "",
        val certification_status: Int? = 0, // 2
        val city: String? = "", // 451400
        val client_manufacturer: Any? = Any(), // null
        val client_system: Any? = Any(), // null
        val client_type: Any? = Any(), // null
        val commend: Boolean? = false, // false
        val commend_check_status: Int? = 0, // 1
        val commend_datetime: String? = "", // 2018-01-02 18:07:36
        val create_datetime: String? = "", // 2018-01-02 18:07:36
        val head_img: String? = "", // http://192.168.7.100:9028/files/img/20191219/201912191148503528.jpg
        val ibean_count: Int? = 0, // 29938
        val id: Int? = 0, // 15
        val id_card: String? = "", // 44123456
        val id_card_front: String? = "", // http://192.168.0.100:9028/files/img/20191022/201910221501189771.jpg
        val id_card_reverse: String? = "", // http://192.168.0.100:9028/files/img/20191022/201910221501190047.jpg
        val interest_tag_id: String? = "", // 747
        val interest_tag_name: String? = "", // 生活服务
        val introduction: String? = "",
        val material_coupon_frozen_price: Int? = 0, // 0
        val material_coupon_price: Int? = 0, // 2992
        val name: String? = "", // 无极
        val nickname: String? = "", // 大大大大
        val number: String? = "", // 60000017
        val pre_commend_datetime: String? = "", // 2018-12-16 15:45:25
        val price: Double? = 0.0, // 522922.44
        val province: String? = "", // 450000
        val qq: String? = "", // 133233255
        val recommend_account_phone: Any? = Any(), // null
        val reserved: Any? = Any(), // null
        val reserved_type: Int? = 0, // 0
        val sex: Int? = 0, // 1
        val subsidy_red_packet: Double? = 0.0, // 9773.8
        val totalIncoming: Double? = 0.0, // 136.1
        val total_outgoing: Double? = 0.0, // 410252.2
        val total_reward_price: Double? = 0.0, // 744774.39
        val total_subsidy_red_packet: Int? = 0, // 0
        val update_datetime: String? = "", // 2019-12-19 11:48:50
        val user_id: Int? = 0, // 17
        val user_type: Int? = 0 // 2
    )

    data class UserOperation(
        val comment_count: Int? = 0, // 576
        val follow_count: Int? = 0, // 32
        val message_count: Int? = 0 // 15
    )

    data class WxUserInfo(
        val headimgurl: String? = "", // http://thirdwx.qlogo.cn/mmopen/vi_32/2fm5CUGvV72aSHZS6dmEbeuEjgyLbMUGGWSvQ32XJmgeI9m7XUdibnx4eiccJDicHyIVnNjbyblISORapg2lZgPLA/132
        val nickname: String? = "" // 小麦
    )
}