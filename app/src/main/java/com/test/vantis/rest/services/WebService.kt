package com.test.vantis.rest.services

import com.test.vantis.models.MainParams
import com.test.vantis.rest.models.OnMainResponse
import com.test.vantis.utils.Constants.Web.API_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface WebService {
    @POST(API_URL + "rest/History")
    fun getData(
        @Header("Authorization") credentials: String,
        @Body body: MainParams
    ): Call<OnMainResponse>
}