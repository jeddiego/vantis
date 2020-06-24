package com.test.vantis.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.test.vantis.viewmodels.MainViewModel
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class ViewModelModule {

    @Provides
    fun provideMainViewModel(fragmentActivity: FragmentActivity): MainViewModel {
        return ViewModelProviders.of(fragmentActivity).get(MainViewModel::class.java)
    }
}