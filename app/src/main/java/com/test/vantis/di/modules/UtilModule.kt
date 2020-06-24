package com.test.vantis.di.modules

import android.content.Context
import com.test.vantis.providers.AppExecutors
import com.test.vantis.providers.AppFeaturesProvider
import com.test.vantis.providers.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class UtilModule {
    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Provides
    @Singleton
    fun providerResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    @Singleton
    fun provideAppFeaturesProvider(context: Context): AppFeaturesProvider {
        return AppFeaturesProvider(context)
    }
}