package com.example.appstock.data.model

import com.google.gson.annotations.SerializedName

data class stockModel(
    @SerializedName("status")  val status: Boolean,
    @SerializedName("response")val response: List<response>
)