package com.labralab.mkbpro10.ui.fragment.account

import com.labralab.mkbpro10.model.repository.LocalUserStore
import com.labralab.mkbpro10.ui.base.BasePresenter
import javax.inject.Inject

class AccountPresenter
@Inject constructor(private val accountRouter: AccountRouter,
                    private val localUserStore: LocalUserStore): BasePresenter<AccountContract.View>(), AccountContract.Presenter {

    override fun onBind() {
        super.onBind()

        view?.showData(localUserStore.getUser())
        view?.stopRefreshing()
    }
}