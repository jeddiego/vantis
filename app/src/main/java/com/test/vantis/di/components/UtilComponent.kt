package com.test.vantis.di.components

import android.content.Context
import com.test.vantis.di.modules.UtilModule
import com.test.vantis.providers.AppExecutors
import com.test.vantis.providers.AppFeaturesProvider
import com.test.vantis.providers.ResourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilModule::class])
interface UtilComponent {

    val appContext: Context

    val appExecutors: AppExecutors

    val resourceProvider: ResourceProvider

    val appFeaturesProvider: AppFeaturesProvider
}