package com.labralab.mkbpro10.implementation.router

import com.labralab.mkbpro10.ui.activity.MainRouter
import com.labralab.mkbpro10.ui.router.BaseRouter
import com.labralab.mkbpro10.ui.router.Screens
import javax.inject.Inject

/**
 * Created by pc on 17.01.2019.
 */

class MainRouterImpl @Inject constructor(private val router: BaseRouter): MainRouter {

    override fun openAccount() {
        router.replaceScreen(Screens.ACCOUNT_FRAGMENT_KAY)
    }

    override fun openCatalog() {
        router.replaceScreen(Screens.CATALOG_FRAGMENT_KAY)
    }

    override fun openLogin() {
        router.replaceScreen(Screens.LOGIN_FRAGMENT_KAY)
    }
}
