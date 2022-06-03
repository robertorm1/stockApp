package com.example.appstock.data.network

import com.example.appstock.core.RetrofitHelper
import com.example.appstock.data.model.stockModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class stockService {

    private val retrofit: Retrofit = RetrofitHelper.getRetrofit()

    suspend fun getDogs(): stockModel {
        return withContext(Dispatchers.IO){
            val call = retrofit.create(routes::class.java).getDogs("/getStock")
            call.body()!!
        }

    }
}