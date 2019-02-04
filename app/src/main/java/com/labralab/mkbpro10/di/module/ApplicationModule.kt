package com.labralab.mkbpro10.di.module

import android.content.Context
import com.labralab.mkbpro10.implementation.store.DiskAccountStoreImpl
import com.labralab.mkbpro10.implementation.store.PreferenceManagerImpl
import com.labralab.mkbpro10.model.store.AccountStore
import com.labralab.mkbpro10.model.store.MKB10Store
import com.labralab.mkbpro10.model.store.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.labralab.mkbpro10.implementation.repositiry.LocalUserStoreImpl
import com.labralab.mkbpro10.implementation.store.MKB10csvStoreImpl
import com.labralab.mkbpro10.model.repository.LocalUserStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class ApplicationModule {

    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        internal fun provideUploadFilePreferenceManager(context: Context): PreferenceManager {
            return PreferenceManagerImpl(context, PreferenceManager.APP_PREFERENCES_NAME)
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

        @Singleton
        @Provides
        @JvmStatic
        internal fun provideFirebaseDaabase(): FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }
    }

    @Binds
    internal abstract fun provideAccountStore(accountStore: DiskAccountStoreImpl): AccountStore

    @Binds
    internal abstract fun provideUserStore(localUserStoreImpl: LocalUserStoreImpl): LocalUserStore

    @Binds
    internal abstract fun provideMKB10Store(mcbMKB10csvStoreImpl: MKB10csvStoreImpl): MKB10Store
}
