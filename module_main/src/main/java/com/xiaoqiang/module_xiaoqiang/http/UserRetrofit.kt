import com.xiaoqiang.kotlin.baselibs.http.RetrofitFactory
import com.xiaoqiang.module_xiaoqiang.http.UserApi

/**
 * @author 小强
 *
 * @time 2020/5/11 11:52
 *
 * @desc Retrofit
 *
 */

object UserRetrofit : RetrofitFactory<UserApi>() {

    override fun getService(): Class<UserApi> = UserApi::class.java

}