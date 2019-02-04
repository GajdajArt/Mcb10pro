package com.labralab.mkbpro10.application

import com.labralab.mkbpro10.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FarmApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .withContext(this)
                .create(this)
    }
}