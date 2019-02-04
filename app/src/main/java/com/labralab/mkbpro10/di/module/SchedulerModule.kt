package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.di.Schedulers
import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
class SchedulerModule {

    @Singleton
    @Provides
    @Named(Schedulers.IO_SCHEDULER)
    internal fun provideIoScheduler(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    @Singleton
    @Provides
    @Named(Schedulers.UI_SCHEDULER)
    internal fun provideUiThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
