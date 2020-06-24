package com.test.vantis.di.components

import com.test.vantis.di.modules.RepositoryModule
import com.test.vantis.viewmodels.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {

    fun inject(viewModel: MainViewModel?)
}