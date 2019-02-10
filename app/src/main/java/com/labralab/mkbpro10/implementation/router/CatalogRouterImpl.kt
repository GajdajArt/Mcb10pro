package com.labralab.mkbpro10.implementation.router

import com.labralab.mkbpro10.ui.fragment.catalog.CatalogRouter
import com.labralab.mkbpro10.ui.router.BaseRouter
import com.labralab.mkbpro10.ui.router.Screens
import javax.inject.Inject

class CatalogRouterImpl
@Inject constructor(private val baseRouter: BaseRouter): CatalogRouter {

    override fun back() {
        baseRouter.back()
    }

    override fun openNextSection(parent: String) {
        baseRouter.addNewScreen(Screens.CATALOG_FRAGMENT_KAY, parent)
    }

    override fun openAccount() {
        baseRouter.addNewScreen(Screens.ACCOUNT_FRAGMENT_KAY)
    }
}