package com.labralab.mkbpro10.model.repository

import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.entity.User
import io.reactivex.Completable

interface LoginRepository {

    fun loginAccount(account: Account, callBack: LoginCallBack, loginErrorCallBack: LoginErrorCallBack): Completable

    fun sineOut(): Completable

    interface LoginCallBack {
        fun onLogin(user: User)
    }

    interface LoginErrorCallBack {
        fun onError(throwable: Throwable)
    }
}