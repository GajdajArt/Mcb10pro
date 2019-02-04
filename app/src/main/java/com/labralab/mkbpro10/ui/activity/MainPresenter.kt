package com.labralab.mkbpro10.ui.activity

import com.labralab.mkbpro10.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by pc on 17.01.2019.
 */

class MainPresenter
@Inject constructor(private val mainRouter: MainRouter):
        BasePresenter<MainContract.View>(),
        MainContract.Presenter {

    override fun onExitClick() {
       mainRouter.openLogin()
    }

    override fun onCatalogClick() {
        mainRouter.openCatalog()
    }

    override fun onAccountClick() {
        mainRouter.openAccount()
    }

    override fun onBind() {
        super.onBind()

        mainRouter.openLogin()
    }
}
