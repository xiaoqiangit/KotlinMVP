package com.xiaoqiang.modelu_drainage.ui

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.lxj.xpopup.interfaces.SimpleCallback
import com.orhanobut.logger.Logger
import com.xiaoqiang.kotlin.baselibs.base.BaseFragment
import com.xiaoqiang.library_common.router.RouterFragmentPath
import com.xiaoqiang.library_common.widget.ToastUtils
import com.xiaoqiang.modelu_drainage.R

/**
 * @author 小强
 *
 * @time 2020/5/12  11:28
 *
 * @desc 引流页面
 *
 */

@Route(path = RouterFragmentPath.Drainage.PAGER_DRAINAGE_MAIN_FRAGMENT)
class DrainageMainFragment: BaseFragment(){

    /**
     * 加载布局
     */
    override fun attachLayoutId(): Int = R.layout.drainage_farament_mian

    /**
     * 懒加载
     */
    override fun lazyLoad() {


        XPopup.Builder(context) //                         .dismissOnTouchOutside(false)
            //                         .autoDismiss(false)
            //                        .popupAnimation(PopupAnimation.NoAnimation)
            .setPopupCallback(object : SimpleCallback() {
                override fun onCreated() {
           Logger.d("onCreated---->:$" )
                }

                override fun onShow() {
                    Logger.d("onShow---->:$" )
                }

                override fun onDismiss() {
                    Logger.d("onDismiss---->:$" )

                }

                //如果你自己想拦截返回按键事件，则重写这个方法，返回true即可
                override fun onBackPressed(): Boolean {

                    return true
                }
            }).asConfirm(
                "我是标题", "床前明月光，疑是地上霜；举头望明月，低头思故乡。",
                "取消", "确定",
                OnConfirmListener { ToastUtils.showToast("click")}, null, false
            )
            .show()
    }

}