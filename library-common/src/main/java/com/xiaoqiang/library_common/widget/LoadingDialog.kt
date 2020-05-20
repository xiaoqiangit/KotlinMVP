package com.xiaoqiang.library_common.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import com.github.ybq.android.spinkit.style.Wave
import com.xiaoqiang.library_common.R
import kotlinx.android.synthetic.main.common_layout_loading_dialog.*


/**
 * @author 小强
 *
 * @time 2020/5/7  14:15
 *
 * @desc 加载框工具类
 *
 */
class LoadingDialog : Dialog {

    constructor(context: Context) : super(context)

    constructor(context: Context, theme: Int) : super(context, theme)

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        loadingBar.setIndeterminateDrawable(Wave())
        super.onWindowFocusChanged(hasFocus)
    }

    companion object {

        fun showDialog(context: Context, cancelable: Boolean): LoadingDialog? {
            return showDialog(context, null, cancelable)
        }

        fun showDialog(context: Context, message: CharSequence?, cancelable: Boolean): LoadingDialog? {
            val dialog = LoadingDialog(context, R.style.CommonLoadingDialog)
            dialog.setContentView(R.layout.common_layout_loading_dialog)
            if (message.isNullOrBlank()) {
                dialog.tv_load_msg?.visibility = View.GONE
            } else {
                dialog.tv_load_msg?.visibility = View.VISIBLE
                dialog.tv_load_msg?.text = message
            }
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(cancelable)
            dialog.window?.attributes?.gravity = Gravity.CENTER
            val lp = dialog.window?.attributes
            lp?.dimAmount = 0.2f
            dialog.window?.attributes = lp
            dialog.show()
            return dialog
        }
    }

}