package com.labralab.mkbpro10.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_account.*

abstract class BaseFragment : DaggerFragment(), BaseContract.View {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutRes, container, false)
        return view
    }

    override fun stopRefreshing() {
        swipeRefreshLayout?.let {
            it.isRefreshing = false
        }
    }

    override fun showProgressDialog() {
        (activity as BaseActivity).showProgressDialog()
    }

    override fun hideProgressDialog() {
        (activity as BaseActivity).hideProgressDialog()
    }

    override fun showUnknownError() {
        (activity as BaseActivity).showUnknownError()
    }

    override fun showMessage(message: String) {
        (activity as BaseActivity).showMessage(message)
    }

    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
