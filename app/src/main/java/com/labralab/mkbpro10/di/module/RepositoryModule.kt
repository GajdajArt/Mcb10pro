package com.labralab.mkbpro10.di.module

import com.labralab.mkbpro10.implementation.repositiry.CatalogRepositoryImpl
import com.labralab.mkbpro10.implementation.repositiry.LoginRepositoryImpl
import com.labralab.mkbpro10.implementation.repositiry.RemoteUserRepository
import com.labralab.mkbpro10.model.repository.LoginRepository
import com.labralab.mkbpro10.model.repository.UserRepository
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun provideCatalogRepository(catalogRepositoryImpl: CatalogRepositoryImpl): CatalogRepository

    @Binds
    abstract fun provideUserRepository(remoteUserRepository: RemoteUserRepository): UserRepository
}