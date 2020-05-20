package com.xiaoqiang.library_common.utils

import com.xiaoqiang.library_common.bean.EventBusHelp
import org.greenrobot.eventbus.EventBus


/**
 * @author 小强
 *
 * @time 2020/5/10  12:19
 *
 * @desc EventBus工具类
 *
 */
object EventBusUtil {

    /**
     * 注册EventBus
     */
    fun register(subscriber: Any?) {
        EventBus.getDefault().register(subscriber)
    }

    /**
     * 销毁EventBus
     */
    fun unregister(subscriber: Any?) {
        EventBus.getDefault().unregister(subscriber)
    }

    /**
     * 发送EventBus
     */
    fun sendEvent(event: EventBusHelp?) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发送粘性EventBus
     */
    fun sendStickyEvent(event: EventBusHelp?) {
        EventBus.getDefault().postSticky(event)
    }
}