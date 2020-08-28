package com.nixesea.diffutillexample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixesea.diffutillexample.Repository
import com.nixesea.diffutillexample.model.Content
import com.nixesea.diffutillexample.model.Response
import com.nixesea.diffutillexample.network.ApiBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainListViewModel : ViewModel() {

    private val _networkResponse = MutableLiveData<Response>()
    val netwotkResponse = _networkResponse

    fun getNewResponse() {
        viewModelScope.launch {
            Repository.getList()
                .catch { exception ->
                    _networkResponse.value = Response.Error(exception.message.toString())
                }
                .collect { list ->
                    _networkResponse.value = Response.Success(list)
                }
        }
    }

}