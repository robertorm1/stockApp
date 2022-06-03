package com.example.appstock.data

import com.example.appstock.data.model.stockModel
import com.example.appstock.data.network.stockService

class stockRepository {
    private val api= stockService()

    suspend fun getAll(): stockModel {
        return api.getDogs();
    }
}