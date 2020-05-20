package com.xiaoqiang.kotlin.baselibs.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.Utils
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper


/**
 * @author 小强
 *
 * @time 2019/9/11  14:32
 *
 * @desc 滑动关闭
 *
 */
 abstract class BaseSwipeBackActivity : FragmentActivity() , SwipeBackActivityBase {


    private var mHelper : SwipeBackActivityHelper? = null


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mHelper = SwipeBackActivityHelper(this)
        mHelper?.onActivityCreate()
    }

    override fun onPostCreate(savedInstanceState : Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper?.onPostCreate()
    }



    override fun <T : View?> findViewById(id : Int) : T {
        val v = super.findViewById<T>(id)
        return if (v == null && mHelper != null) mHelper?.findViewById(id) as T else v
    }

    override fun getSwipeBackLayout() : SwipeBackLayout {
        return mHelper!!.swipeBackLayout
    }

    /**
     * 禁止滑动退出
     * @param enable false 禁止滑动退出
     */
    override fun setSwipeBackEnable(enable : Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        swipeBackLayout.scrollToFinishActivity()
    }

}