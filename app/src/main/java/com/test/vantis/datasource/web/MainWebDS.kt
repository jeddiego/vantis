package com.test.vantis.datasource.web

import androidx.lifecycle.Observer
import com.test.vantis.di.components.DaggerFrameworkComponent
import com.test.vantis.models.MainParams
import com.test.vantis.rest.services.WebService
import com.test.vantis.rest.utils.ApiCallback
import okhttp3.Credentials
import javax.inject.Inject

class MainWebDS {
    @Inject
    lateinit var webService: WebService

    init {
        DaggerFrameworkComponent.builder().build().inject(this)
    }

    fun getData(
        startDate: String,
        endDate: String,
        observer: Observer<Any>
    ) {
        webService.getData(
            Credentials.basic("checkin_vantis", "V4nt1s.$18"),
            MainParams(
                "379",
                startDate,
                endDate
            )
        ).enqueue(ApiCallback(observer))
    }
}