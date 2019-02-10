package com.labralab.mkbpro10.ui.router

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.ui.activity.MainActivity
import com.labralab.mkbpro10.ui.fragment.account.AccountFragment
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogFragment
import com.labralab.mkbpro10.ui.fragment.login.LoginFragment


class BaseRouter(private val activity: MainActivity) {

    private val fragmentManager: FragmentManager = activity.supportFragmentManager

    private val screenMap = HashMap<String, Fragment>()

    init {
        screenMap[Screens.LOGIN_FRAGMENT_KAY] = LoginFragment()
        screenMap[Screens.CATALOG_FRAGMENT_KAY] = CatalogFragment()
        screenMap[Screens.ACCOUNT_FRAGMENT_KAY] = AccountFragment()
    }

    fun addNewScreen(screenKay: String) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, screenMap[screenKay]!!)
                .addToBackStack("")
                .commit()
    }

    fun addNewScreen(screenKay: String, data: Any?) {
        val fragment = if (screenKay == Screens.CATALOG_FRAGMENT_KAY && data != null) {
            CatalogFragment.getInstance(data)
        } else {
            screenMap[screenKay]!!
        }

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack("")
            .commit()
    }


    fun openScreen(screenKay: String) {
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, screenMap[screenKay]!!)
                .commit()
    }

    fun replaceScreen(screenKay: String) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, screenMap[screenKay]!!)
                .commit()
    }

    fun back(){
        fragmentManager.popBackStack()
    }
}
