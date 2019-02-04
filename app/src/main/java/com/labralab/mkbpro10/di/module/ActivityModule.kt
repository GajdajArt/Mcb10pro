package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.di.module.FragmentModule
import com.labralab.mkbpro10.di.module.RouterModule
import com.labralab.mkbpro10.ui.activity.MainActivity
import com.labralab.mkbpro10.di.scope.ActivityScope

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class, RouterModule::class])
    @ActivityScope
    abstract fun mainActivity(): MainActivity
}
