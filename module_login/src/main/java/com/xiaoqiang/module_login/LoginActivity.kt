package com.xiaoqiang.module_login

import com.alibaba.android.arouter.facade.annotation.Route
import com.xiaoqiang.kotlin.baselibs.base.BaseMvpActivity
import com.xiaoqiang.kotlin.baselibs.base.BaseMvpRecycleViewActivity
import com.xiaoqiang.library_common.router.RouterActivityPath
import com.xiaoqiang.module_login.mvp.contract.LoginContract
import com.xiaoqiang.module_login.mvp.presenter.LoginPresenter
import java.util.HashMap

@Route(path = RouterActivityPath.Login.PAGER_LOGIN)
class LoginActivity : BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {


    /**
     * 创建当前Presenter
     */
    override fun createPresenter() : LoginContract.Presenter = LoginPresenter()


    /**
     * 布局文件id
     */
    override fun attachLayoutId() : Int = R.layout.user_activity_login

    /**
     * 登录成功
     */
    override fun loginSuccess(message : String) {
        showMsg(message)
    }


    /**
     * 开始请求
     */
    override fun start() {
        super.start()
        val map : HashMap<String?, Any?> = hashMapOf()
        map["username"] = "18822223335"
        map["password"] = "abc123456781"
        mPresenter?.requestLogin(map)
    }



}
