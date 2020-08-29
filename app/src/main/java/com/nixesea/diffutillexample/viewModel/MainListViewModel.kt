package com.nixesea.diffutillexample.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nixesea.diffutillexample.Repository
import com.nixesea.diffutillexample.model.ListItemsType
import com.nixesea.diffutillexample.model.Response
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainListViewModel : ViewModel() {

    private val _networkResponse = MutableLiveData<Response>()
    val netwotkResponse = _networkResponse

    fun getNewResponse() {
        viewModelScope.launch {
            Repository.getList()
                .catch { exception ->
                    exception.printStackTrace()
                    _networkResponse.value = Response.Error(exception.message.toString())
                }
                .collect { list ->
                    _networkResponse.value = Response.Success(ArrayList<ListItemsType>(list))
                }
        }
    }

}