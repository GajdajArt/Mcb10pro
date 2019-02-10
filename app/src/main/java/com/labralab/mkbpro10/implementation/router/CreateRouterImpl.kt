package com.labralab.mkbpro10.implementation.router

import com.labralab.mkbpro10.ui.fragment.create.CreateRouter
import com.labralab.mkbpro10.ui.router.BaseRouter
import com.labralab.mkbpro10.ui.router.Screens
import javax.inject.Inject

class CreateRouterImpl
@Inject constructor(private val baseRouter: BaseRouter): CreateRouter {

    override fun openCatalog() {
        baseRouter.back()
        baseRouter.replaceScreen(Screens.CATALOG_FRAGMENT_KAY)
    }
}