package com.example.appstock.data.network

import com.example.appstock.data.model.stockModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface routes {
    @GET
    suspend fun getDogs(@Url url:String): Response<stockModel>
}