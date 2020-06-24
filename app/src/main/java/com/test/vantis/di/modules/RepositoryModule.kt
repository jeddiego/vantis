package com.test.vantis.di.modules

import com.test.vantis.domains.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(): MainRepository {
        return MainRepository()
    }
}