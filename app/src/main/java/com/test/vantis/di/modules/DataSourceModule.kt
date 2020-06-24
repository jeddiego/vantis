package com.test.vantis.di.modules

import com.test.vantis.datasource.web.MainWebDS
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DataSourceModule {

    @Singleton
    @Provides
    fun provideMainWebDS(): MainWebDS {
        return MainWebDS()
    }

}