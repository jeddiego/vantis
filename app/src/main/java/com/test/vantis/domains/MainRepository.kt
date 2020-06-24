package com.test.vantis.domains

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.test.vantis.rest.models.OnMainResponse
import com.test.vantis.MainApplication
import com.test.vantis.R
import com.test.vantis.datasource.web.MainWebDS
import com.test.vantis.di.modules.ContextModule
import javax.inject.Inject

class MainRepository {
    private val page = 1

    @Inject
    lateinit var webDS: MainWebDS

    init {
        DaggerDataSourceComponent.builder()
            .contextModule(ContextModule(MainApplication.appContext)).build().inject(this)
    }

    fun getData(): LiveData<String> {
       if (!MainApplication.appFeaturesProvider.hasInternetConnection()) {
                    showMessage(R.string.no_internet)
                } else {
                    getFromNetwork()
                }
    }

    private fun showMessage(idMessage: Int) {
        Toast.makeText(
            MainApplication.appContext,
            MainApplication.appResourceProvider.getString(idMessage),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getFromNetwork(): MutableLiveData<String> {
        val movies = MutableLiveData<String>()
            webDS.getData(Observer {
                if (it !is OnMainResponse) {
                    return@Observer
                }
                movies.postValue(it.data)
            })

        return movies
    }
}