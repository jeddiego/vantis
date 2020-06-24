package com.test.vantis.di.components

import com.test.vantis.activities.MainActivity
import com.test.vantis.di.modules.ViewModelModule
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun inject(view: MainActivity)
}