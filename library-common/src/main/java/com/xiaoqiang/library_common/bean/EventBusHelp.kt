package com.xiaoqiang.library_common.bean

import java.io.Serializable

/**
 * @author 小强
 *
 * @time 2020/5/10  12:05
 *
 * @desc EventBus实体类
 *
 */
class EventBusHelp : Serializable {

    constructor() {}

    //eventbus标签，用来区分
    private var tag: String? = null

    //eventbus 传递的结果
    private var result: Any? = null

    fun getTag(): String? {
        return tag
    }

    fun setTag(tag: String?) {
        this.tag = tag
    }

    fun getResult(): Any? {
        return result
    }

    fun setResult(result: Any?) {
        this.result = result
    }


    constructor(tag: String?) {
        setTag(tag)
        setResult("")
    }

    constructor(tag: String?, result: Any?) {
        setTag(tag)
        setResult(result)
    }
}
