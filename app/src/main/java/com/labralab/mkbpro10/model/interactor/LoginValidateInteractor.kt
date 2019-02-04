package com.labralab.mkbpro10.model.interactor

import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.model.interactor.base.BaseCompletableInteractor
import io.reactivex.Completable
import javax.inject.Inject

class LoginValidateInteractor @Inject constructor(): BaseCompletableInteractor<Account>() {

    override fun buildCompletable(params: Account?): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
