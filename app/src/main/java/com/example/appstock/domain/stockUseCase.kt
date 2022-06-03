package com.example.appstock.domain

import com.example.appstock.data.model.stockModel
import com.example.appstock.data.stockRepository

class stockUseCase {

    private val repository=stockRepository()

    suspend operator fun invoke():stockModel=repository.getAll()
}