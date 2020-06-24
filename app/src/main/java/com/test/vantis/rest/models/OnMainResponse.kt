package com.test.vantis.rest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OnMainResponse(
    @SerializedName("results")
    @Expose
    val data: String
)