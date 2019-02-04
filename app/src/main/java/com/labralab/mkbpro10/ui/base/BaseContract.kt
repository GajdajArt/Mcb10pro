package com.labralab.mkbpro10.ui.base

interface BaseContract {
    interface View {

        fun showProgressDialog()

        fun hideProgressDialog()

        fun showUnknownError()

        fun showMessage(message: String)

        fun stopRefreshing()
    }

    interface Presenter<V : View> {

        fun bind(view: V)

        fun unbind()
    }
}
