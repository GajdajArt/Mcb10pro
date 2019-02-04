package com.labralab.mkbpro10.ui.fragment.account

import android.annotation.SuppressLint
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*
import javax.inject.Inject

class AccountFragment : BaseFragment(), AccountContract.View {

    @Inject
    lateinit var presenter: AccountContract.Presenter

    @SuppressLint("SetTextI18n")
    override fun showData(user: User?) {
        user?.let {
            nameTV.text = "${it.firstName} ${it.secondName}"
            pointsTV.text = it.point
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_account

    override fun onStart() {
        super.onStart()
        presenter.bind(this)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.bind(this)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }
}