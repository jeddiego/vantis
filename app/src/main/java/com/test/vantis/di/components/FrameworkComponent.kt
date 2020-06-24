package com.test.vantis.di.components

import com.test.vantis.datasource.web.MainWebDS
import com.test.vantis.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface FrameworkComponent {

    fun inject(mainWebDS: MainWebDS)

}