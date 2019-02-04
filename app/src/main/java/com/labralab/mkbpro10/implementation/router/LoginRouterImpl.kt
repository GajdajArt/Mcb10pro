package com.labralab.mkbpro10.implementation.router

import com.labralab.mkbpro10.ui.fragment.login.LoginRouter
import com.labralab.mkbpro10.ui.router.BaseRouter
import com.labralab.mkbpro10.ui.router.Screens
import javax.inject.Inject

class LoginRouterImpl @Inject constructor(private val baseRouter: BaseRouter): LoginRouter {

    override fun openCatalog() {
        baseRouter.replaceScreen(Screens.CATALOG_FRAGMENT_KAY)
    }
}
