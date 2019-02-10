package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.di.scope.FragmentScope
import com.labralab.mkbpro10.ui.fragment.account.AccountFragment
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogFragment
import com.labralab.mkbpro10.ui.fragment.create.CreateFragment
import com.labralab.mkbpro10.ui.fragment.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun catalogFragment(): CatalogFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun accountFragment(): AccountFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun createFragment(): CreateFragment
}
