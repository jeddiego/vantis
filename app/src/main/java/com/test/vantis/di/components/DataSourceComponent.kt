package com.test.vantis.di.components

import com.test.vantis.di.modules.DataSourceModule
import com.test.vantis.domains.MainRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataSourceModule::class])
interface DataSourceComponent {

    fun inject(mainRepository: MainRepository)

}