package com.xiaoqiang.library_common.bean

import com.squareup.moshi.Json

/**
 * @author 小强
 *
 * @time 2020/5/15  11:01
 *
 * @desc 网络请求数据
 *
 */
data class HttpResult<T>(@Json(name = "data") val data: T) : BaseEntity() {


}