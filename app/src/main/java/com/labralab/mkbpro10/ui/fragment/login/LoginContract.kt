package com.labralab.mkbpro10.ui.fragment.login

import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.ui.base.BaseContract

interface LoginContract: BaseContract {

    interface View: BaseContract.View {

        fun hideActionBar()

        fun showActionBar()
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun onLoginClick(account: Account)
    }
}
