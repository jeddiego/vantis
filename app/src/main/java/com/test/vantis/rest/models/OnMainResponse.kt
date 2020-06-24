package com.test.vantis.rest.models

import com.google.gson.annotations.SerializedName

data class OnMainResponse(
    @SerializedName("responseObject")
    val responseObject: String = "",
    @SerializedName("codeMessage")
    val codeMessage: String,
    @SerializedName("responseMessage")
    val responseMessage: String
)