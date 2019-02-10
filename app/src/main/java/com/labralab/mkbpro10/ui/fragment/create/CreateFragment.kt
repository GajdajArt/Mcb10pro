package com.labralab.mkbpro10.ui.fragment.create

import android.os.Bundle
import android.view.View
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.activity.MainActivity
import com.labralab.mkbpro10.ui.base.BaseContract
import com.labralab.mkbpro10.ui.base.BaseFragment
import com.labralab.mkbpro10.ui.fragment.login.LoginContract
import kotlinx.android.synthetic.main.fragment_create.*
import javax.inject.Inject

class CreateFragment: BaseFragment(), CreateContract.View {

    @Inject
    lateinit var presenter: CreateContract.Presenter

    lateinit var activity: MainActivity

    override val layoutRes: Int
        get() = R.layout.fragment_create

    override fun showValidationErrors() {
        if(nameET.text.isNullOrBlank()) {
            nameTIL.error = "Введите ваше имя"
        }

        if(surnameET.text.isNullOrBlank()) {
            surnameTIL.error = "Введите вашу фамилию"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = getActivity() as MainActivity

        saveButton.setOnClickListener {
            presenter.saveData(createUser())
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
        activity.getMainSupportActionBar()?.hide()
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
        activity.getMainSupportActionBar()?.show()
    }

    private fun createUser(): User {
        val user = User()
        user.firstName = nameET.text.toString()
        user.secondName = surnameET.text.toString()
        return user
    }

    override fun ctearErrors() {
        nameTIL.error = ""
        surnameTIL.error = ""
    }
}