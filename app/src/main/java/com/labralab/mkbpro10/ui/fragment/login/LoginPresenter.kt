package com.labralab.mkbpro10.ui.fragment.login

import android.annotation.SuppressLint
import com.labralab.mkbpro10.di.Schedulers
import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.model.repository.LocalUserStore
import com.labralab.mkbpro10.model.repository.LoginRepository
import com.labralab.mkbpro10.model.repository.UserRepository
import com.labralab.mkbpro10.model.store.MKB10Store
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
                    private val mkB10Store: MKB10Store,
                    private val localUserStore: LocalUserStore,
                    @Named(Schedulers.IO_SCHEDULER) private val ioScheduler: Scheduler,
                    @Named(Schedulers.UI_SCHEDULER) private val uiScheduler: Scheduler):
        BasePresenter<LoginContract.View>(),
        LoginContract.Presenter {

    private val LOGIN_DISPOSABLE_KAY = 1
    private val CHECK_USER_DISPOSABLE_KAY = 2

    override fun onBind() {
        super.onBind()

        view?.hideActionBar()
        createCatalog()
    }

    @SuppressLint("CheckResult")
    private fun createCatalog() {
        mkB10Store.getMKBList("")
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({},{})
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
        checkIsUserCreated(user)
    }

    private fun checkIsUserCreated(user: User) {
        subscribe(CHECK_USER_DISPOSABLE_KAY, userRepository.isUserCreated(user)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({onCheckUserResult(it, user)},{processError(it)}))
    }

    private fun onCheckUserResult (created: Boolean, user: User) {
        view?.hideProgressDialog()

        if (created) {
            userRepository.startUserDataChangeListener(user.uid)
            loginRouter.openCatalog()
        } else {
            localUserStore.saveUser(user)
            loginRouter.openCreateScreen()
        }
    }
}

