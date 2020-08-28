package com.nixesea.diffutillexample

import com.nixesea.diffutillexample.model.Content
import com.nixesea.diffutillexample.network.ApiBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object Repository {

    suspend fun getList(): Flow<ArrayList<Content>> {
        return flow {
            emit(ApiBase.apiRequest.allSessions())
        }.flowOn(Dispatchers.IO)
    }

}