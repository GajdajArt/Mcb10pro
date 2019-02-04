package com.labralab.mkbpro10.ui.fragment.login

import android.os.Bundle
import android.view.View
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.model.entity.Account
import com.labralab.mkbpro10.ui.activity.MainActivity
import com.labralab.mkbpro10.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : BaseFragment(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    lateinit var activity: MainActivity

    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity = getActivity() as MainActivity
        initButton()

        title.setOnClickListener {
            loginET.setText("mcbpro10@gmail.com")
            passwordET.setText("123456")
        }
    }

    private fun initButton() {
        loginButton.setOnClickListener {
            presenter.onLoginClick(buildAccount())
        }
    }

    private fun buildAccount(): Account {
        return Account(loginET.text.toString(), passwordET.text.toString())
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }

    override fun hideActionBar() {
        activity.getMainSupportActionBar()?.hide()
    }

    override fun showActionBar() {
        activity.getMainSupportActionBar()?.show()
    }
}
