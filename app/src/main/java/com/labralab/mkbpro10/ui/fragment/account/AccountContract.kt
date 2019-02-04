package com.labralab.mkbpro10.ui.fragment.account

import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.base.BaseContract

interface AccountContract {

    interface View: BaseContract.View {

        fun showData(user: User?)
    }

    interface Presenter: BaseContract.Presenter<View>
}