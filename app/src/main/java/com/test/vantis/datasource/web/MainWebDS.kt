package com.test.vantis.datasource.web

import androidx.lifecycle.Observer
import com.test.vantis.rest.services.WebService
import com.test.vantis.rest.utils.ApiCallback
import javax.inject.Inject

class MainWebDS {
    @Inject
    lateinit var webService: WebService

    init {
        DaggerFrameworkComponent.builder().build().inject(this)
    }

    fun getData(
        observer: Observer<Any>
    ) {
        webService.getData().enqueue(ApiCallback(observer))
    }
}