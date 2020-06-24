package com.test.vantis.domains

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.vantis.rest.models.OnMainResponse
import com.test.vantis.MainApplication
import com.test.vantis.R
import com.test.vantis.datasource.web.MainWebDS
import com.test.vantis.di.components.DaggerDataSourceComponent
import com.test.vantis.di.modules.ContextModule
import javax.inject.Inject

class MainRepository {
    @Inject
    lateinit var webDS: MainWebDS

    init {
        DaggerDataSourceComponent.builder()
            .contextModule(ContextModule(MainApplication.appContext)).build().inject(this)
    }

    fun getData(startDate: String, endDate: String): MutableLiveData<OnMainResponse> {
        return if (!MainApplication.appFeaturesProvider.hasInternetConnection()) {
            val noData = MutableLiveData<OnMainResponse>()
            showMessage()
            noData
        } else {
            getFromNetwork(startDate, endDate)
        }
    }

    private fun showMessage() {
        Toast.makeText(
            MainApplication.appContext,
            MainApplication.appResourceProvider.getString(R.string.no_internet),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getFromNetwork(startDate: String, endDate: String): MutableLiveData<OnMainResponse> {
        val data = MutableLiveData<OnMainResponse>()
        webDS.getData(startDate, endDate, Observer {
            if (it !is OnMainResponse) {
                return@Observer
            }
            data.postValue(it)
        })

        return data
    }
}