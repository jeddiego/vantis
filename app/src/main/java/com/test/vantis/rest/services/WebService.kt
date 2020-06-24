package com.test.vantis.rest.services

import com.test.vantis.rest.models.OnMainResponse
import com.test.vantis.utils.Constants.Web.API_URL
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET(API_URL + "rest/History")
    fun getData(): Call<OnMainResponse>

}