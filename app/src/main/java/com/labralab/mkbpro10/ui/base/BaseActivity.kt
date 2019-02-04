package com.labralab.mkbpro10.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.Toast
import com.labralab.mkbpro10.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.content_main.*

abstract class BaseActivity : DaggerAppCompatActivity(), BaseContract.View {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resId = layoutRes
        if (resId != 0) {
            setContentView(layoutRes)
        }

        hideProgressDialog()
    }

    override fun showProgressDialog() {
        loader?.let {
            it.visibility = View.VISIBLE
        }
    }

    override fun hideProgressDialog() {
        loader?.let {
            it.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        hideProgressDialog()
    }

    override fun stopRefreshing() {
    }

    override fun showUnknownError() {
        Toast.makeText(this, getString(R.string.error_mesage), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
