package com.test.vantis

import android.app.Application
import android.content.Context
import com.test.vantis.di.components.DaggerUtilComponent
import com.test.vantis.di.components.UtilComponent
import com.test.vantis.di.modules.ContextModule
import com.test.vantis.providers.AppExecutors
import com.test.vantis.providers.AppFeaturesProvider
import com.test.vantis.providers.ResourceProvider

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        utilComponent = DaggerUtilComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

    companion object {
        lateinit var utilComponent: UtilComponent

        val appContext: Context
            get() = utilComponent.appContext

        val appResourceProvider: ResourceProvider
            get() = utilComponent.resourceProvider

        val appFeaturesProvider: AppFeaturesProvider
            get() = utilComponent.appFeaturesProvider

        val appExecutors: AppExecutors
            get() = utilComponent.appExecutors
    }
}