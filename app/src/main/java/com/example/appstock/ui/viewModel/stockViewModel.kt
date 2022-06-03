package com.example.appstock.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appstock.data.model.stockModel
import com.example.appstock.domain.stockUseCase
import kotlinx.coroutines.launch

class stockViewModel: ViewModel() {

    val modelStock = MutableLiveData<stockModel>()
    val isloading = MutableLiveData<Boolean>()

    var getCase = stockUseCase()

    fun getStock(){
        isloading.postValue(true)
        viewModelScope.launch {
            val result = getCase()
            modelStock.postValue(result)
            isloading.postValue(false)
        }

    }
}