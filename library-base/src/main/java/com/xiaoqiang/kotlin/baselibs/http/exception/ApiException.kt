package com.xiaoqiang.kotlin.baselibs.http.exception

/**
 * @author 小强
 *
 * @time 2020/5/7  14:04
 *
 * @desc 服务器请求时间太长
 *
 */
class ApiException : RuntimeException {

    private var code: Int? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}