package com.nixesea.diffutillexample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixesea.diffutillexample.model.Response
import com.nixesea.diffutillexample.network.ApiBase
import kotlinx.coroutines.launch

class MainListViewModel : ViewModel() {

    private val _networkResponse = MutableLiveData<ArrayList<Response>>()
    val netwotkResponse = _networkResponse

    fun getNewResponse() {
        viewModelScope.launch {
            val response = ApiBase.apiRequest.allSessions()
            _networkResponse.value = response
        }
    }

}