package com.labralab.mkbpro10.ui.fragment.login

import com.labralab.mkbpro10.di.Schedulers
import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.LoginRepository
import com.labralab.mkbpro10.model.repository.UserRepository
import com.labralab.mkbpro10.ui.base.BasePresenter
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by pc on 17.01.2019.
 */

class LoginPresenter
@Inject constructor(private val loginRouter: LoginRouter,
                    private val loginRepository: LoginRepository,
                    private val userRepository: UserRepository,
                    @Named(Schedulers.IO_SCHEDULER) private val ioScheduler: Scheduler,
                    @Named(Schedulers.UI_SCHEDULER) private val uiScheduler: Scheduler):
        BasePresenter<LoginContract.View>(),
        LoginContract.Presenter {

    private val LOGIN_DISPOSABLE_KAY = 1

    override fun onBind() {
        super.onBind()

        view?.hideActionBar()
    }

    override fun onUnBind() {
        super.onUnBind()
        view?.showActionBar()
    }

    override fun onLoginClick(account: Account) {
        view?.showProgressDialog()
        loginAccount(account)
    }

    private fun loginAccount(account: Account) {
        subscribe(LOGIN_DISPOSABLE_KAY, loginRepository.loginAccount(account,
                callBack = object : LoginRepository.LoginCallBack {
            override fun onLogin(user: User) {
                onLoginSuccessful(user)
            }
        }, loginErrorCallBack = object : LoginRepository.LoginErrorCallBack {
                override fun onError(throwable: Throwable) {
                    processError(throwable)
                }
            })!!.subscribe({},  { processError(it) }))
    }

    private fun onLoginSuccessful(user: User) {
        userRepository.startUserDataChangeListener(user.uid)

        view?.hideProgressDialog()
        loginRouter.openCatalog()
    }
}
