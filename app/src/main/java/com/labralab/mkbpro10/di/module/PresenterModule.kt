package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.ui.activity.MainContract
import com.labralab.mkbpro10.ui.activity.MainPresenter
import com.labralab.mkbpro10.ui.fragment.account.AccountContract
import com.labralab.mkbpro10.ui.fragment.account.AccountPresenter
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogContract
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogPresenter
import com.labralab.mkbpro10.ui.fragment.login.LoginContract
import com.labralab.mkbpro10.ui.fragment.login.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresenterModule {

    @Binds
    abstract fun mainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun lodinPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

    @Binds
    abstract fun catalogPresenter(catalogPresenter: CatalogPresenter): CatalogContract.Presenter

    @Binds
    abstract fun accountPresenter(accountPresenter: AccountPresenter): AccountContract.Presenter
}
