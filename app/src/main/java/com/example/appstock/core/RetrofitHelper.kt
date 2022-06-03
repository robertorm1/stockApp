package com.example.appstock.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nodeaz.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}