package com.labralab.mkbpro10.ui.activity

import com.labralab.mkbpro10.ui.base.BaseContract

interface MainContract: BaseContract {

    interface View: BaseContract.View

    interface Presenter : BaseContract.Presenter<View> {

        fun onExitClick()

        fun onCatalogClick()

        fun onAccountClick()
    }
}
