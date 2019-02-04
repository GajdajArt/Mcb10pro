package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.implementation.router.AccountRouterImpl
import com.labralab.mkbpro10.implementation.router.CatalogRouterImpl
import com.labralab.mkbpro10.implementation.router.LoginRouterImpl
import com.labralab.mkbpro10.implementation.router.MainRouterImpl
import com.labralab.mkbpro10.ui.activity.MainActivity
import com.labralab.mkbpro10.ui.activity.MainRouter
import com.labralab.mkbpro10.ui.fragment.account.AccountRouter
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogRouter
import com.labralab.mkbpro10.ui.fragment.login.LoginRouter
import com.labralab.mkbpro10.ui.router.BaseRouter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RouterModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideBaseRouter(baseActivity: MainActivity): BaseRouter {
            return BaseRouter(baseActivity)
        }
    }

    @Binds
    abstract fun provideMainRouter(MainRouterImpl: MainRouterImpl): MainRouter

    @Binds
    abstract fun provideLoginRouter(loginRouterImpl: LoginRouterImpl): LoginRouter

    @Binds
    abstract fun provideCatalogRouter(catalogRouterImpl: CatalogRouterImpl): CatalogRouter

    @Binds
    abstract fun provideAccountRouter(accountRouter: AccountRouterImpl): AccountRouter
}
