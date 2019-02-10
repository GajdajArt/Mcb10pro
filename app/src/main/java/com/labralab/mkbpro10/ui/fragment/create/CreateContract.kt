package com.labralab.mkbpro10.ui.fragment.create

import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.base.BaseContract

interface CreateContract {

    interface View : BaseContract.View {

        fun showValidationErrors()

        fun ctearErrors()
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun saveData(user: User)
    }
}