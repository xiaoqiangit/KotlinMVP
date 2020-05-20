package com.xiaoqiang.library_common.widget

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.xiaoqiang.library_common.R
import com.xiaoqiang.library_common.config.AppConfig
import java.lang.ref.WeakReference

/**
 * @author 小强
 *
 * @time 2020/5/8  17:13
 *
 * @desc Toast工具类
 *
 */
object ToastUtils {

    private const val DEFAULT_COLOR: Int = Color.CYAN
    private var sToast: Toast? = null

    private var backgroundColor = DEFAULT_COLOR
    private var bgResource: Int = Color.BLACK
    private var messageColor = Color.WHITE
    private var sViewWeakReference: WeakReference<View>? = null

    private var gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
    private var xOffset = 0
    private var yOffset =
        (64 * AppConfig.getApplication().resources.displayMetrics.density + 0.5).toInt()

    private var sHandler = Handler(Looper.getMainLooper())

    private fun ToastUtils() {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * 设置吐司位置
     *
     * @param gravity 位置
     * @param xOffset x偏移
     * @param yOffset y偏移
     */
    fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        ToastUtils.gravity = gravity
        ToastUtils.xOffset = xOffset
        ToastUtils.yOffset = yOffset
    }

    /**
     * 设置吐司view
     *
     * @param layoutId 视图
     */
    fun setView(@LayoutRes layoutId: Int) {
        val inflate =
            AppConfig.getApplication()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        sViewWeakReference =
            WeakReference(inflate.inflate(layoutId, null))
    }

    /**
     * 设置吐司view
     *
     * @param view 视图
     */
    fun setView(view: View?) {
        sViewWeakReference =
            view?.let { WeakReference(it) }
    }

    /**
     * 获取吐司view
     *
     * @return view
     */
    fun getView(): View? {
        if (sViewWeakReference != null) {
            val view = sViewWeakReference?.get()
            if (view != null) {
                return view
            }
        }
        return if (sToast != null) sToast?.view else null
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor 背景色
     */
    fun setBackgroundColor(@ColorInt backgroundColor: Int) {
        ToastUtils.backgroundColor = backgroundColor
    }

    /**
     * 设置背景资源
     *
     * @param bgResource 背景资源
     */
    fun setBgResource(@DrawableRes bgResource: Int) {
        ToastUtils.bgResource = bgResource
    }

    /**
     * 设置消息颜色
     *
     * @param messageColor 颜色
     */
    fun setMessageColor(@ColorInt messageColor: Int) {
        ToastUtils.messageColor = messageColor
    }

    /**
     * 安全地显示短时吐司
     *
     * @param text 文本
     */
    fun showToast(text: CharSequence) {
        sHandler.post { show(text, Toast.LENGTH_SHORT) }
    }

    /**
     * 安全地显示短时吐司
     *
     * @param resId 资源Id
     */
    fun showToast(@StringRes resId: Int) {
        sHandler.post { show(resId, Toast.LENGTH_SHORT) }
    }


    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showToast(format: String) {
        sHandler.post { show(format, Toast.LENGTH_SHORT) }
    }


    /**
     * 安全地显示短时吐司
     *
     * @param format 格式
     * @param view   显示的图片
     * @param format 显示的文字
     */
    fun showToastView(@DrawableRes view: Int, format: String?) {
        sHandler.post {
            val toast = Toast.makeText(AppConfig.getApplication(), format, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            val toastView = toast.view as LinearLayout
            val imageCodeProject =
                ImageView(AppConfig.getApplication())
            imageCodeProject.setImageResource(view)
            //		toastView.setRotationX(180);  //绕X轴翻转180度
            toastView.addView(imageCodeProject, 0)
            toast.show()
        }
    }


    /**
     * 安全地显示长时吐司
     *
     * @param text 文本
     */
    fun showLongSafe(text: CharSequence) {
        sHandler.post { show(text, Toast.LENGTH_LONG) }
    }

    /**
     * 安全地显示长时吐司
     *
     * @param resId 资源Id
     */
    fun showLongSafe(@StringRes resId: Int) {
        sHandler.post { show(resId, Toast.LENGTH_LONG) }
    }


    /**
     * 安全地显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showLongSafe(format: String) {
        sHandler.post { show(format, Toast.LENGTH_LONG) }
    }

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    fun showShort(text: CharSequence) {
        show(text, Toast.LENGTH_SHORT)
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    fun showShort(@StringRes resId: Int) {
        show(resId, Toast.LENGTH_SHORT)
    }


    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showShort(format: String) {
        show(format, Toast.LENGTH_SHORT)
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    fun showLong(text: CharSequence) {
        show(text, Toast.LENGTH_LONG)
    }

    /**
     * 显示长时吐司
     *
     * @param resId 资源Id
     */
    fun showLong(@StringRes resId: Int) {
        show(resId, Toast.LENGTH_LONG)
    }


    /**
     * 显示长时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    fun showLong(format: String) {
        show(format, Toast.LENGTH_LONG)
    }

    /**
     * 安全地显示短时自定义吐司
     */
    fun showCustomShortSafe() {
        sHandler.post { show("", Toast.LENGTH_SHORT) }
    }

    /**
     * 安全地显示长时自定义吐司
     */
    fun showCustomLongSafe() {
        sHandler.post { show("", Toast.LENGTH_LONG) }
    }

    /**
     * 显示短时自定义吐司
     */
    fun showCustomShort() {
        show("", Toast.LENGTH_SHORT)
    }

    /**
     * 显示长时自定义吐司
     */
    fun showCustomLong() {
        show("", Toast.LENGTH_LONG)
    }

    /**
     * 显示吐司
     *
     * @param resId    资源Id
     * @param duration 显示时长
     */
    private fun show(@StringRes resId: Int, duration: Int) {
        show(AppConfig.getApplication().resources.getText(resId).toString(), duration)
    }


    /**
     * 显示吐司
     *
     * @param text     文本
     * @param duration 显示时长
     */
    private fun show(text: CharSequence, duration: Int) {

        if (text.isNullOrEmpty()) {
            return
        }

        cancel()
        val inflate = AppConfig.getApplication()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view1: View = inflate.inflate(R.layout.common_tosta_view, null)
        val tvToast = view1.findViewById<View>(R.id.toast_text) as TextView
        tvToast.text = text
        sViewWeakReference = WeakReference(view1)
        var isCustom = false
        if (sViewWeakReference != null) {
            val view = sViewWeakReference?.get()
            if (view != null) {
                sToast = Toast(AppConfig.getApplication())
                sToast?.view = view
                sToast?.duration = duration
                isCustom = true
            }
        }
        if (!isCustom) {
            sToast = if (messageColor != DEFAULT_COLOR) {
                val spannableString = SpannableString(text)
                val colorSpan = ForegroundColorSpan(messageColor)
                spannableString.setSpan(
                    colorSpan,
                    0,
                    spannableString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                Toast.makeText(AppConfig.getApplication(), spannableString, duration)
            } else {
                Toast.makeText(AppConfig.getApplication(), text, duration)
            }
        }
        //        View view = sToast.getView();
//        if (bgResource != -1) {
//            view.setBackgroundResource(bgResource);
//        } else if (backgroundColor != DEFAULT_COLOR) {
//            view.setBackgroundColor(backgroundColor);
//        }
        sToast?.setGravity(gravity, xOffset, yOffset)
        sToast?.show()
    }

    /**
     * 取消吐司显示
     */
    private fun cancel() {
        if (sToast != null) {
            sToast?.cancel()
            sToast = null
        }
    }
}