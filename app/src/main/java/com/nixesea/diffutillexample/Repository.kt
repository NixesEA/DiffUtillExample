package com.nixesea.diffutillexample

import com.nixesea.diffutillexample.model.ListItemsType
import com.nixesea.diffutillexample.model.Mapper.Companion.toListItemsType
import com.nixesea.diffutillexample.network.ApiBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object Repository {

    suspend fun getList(): Flow<ArrayList<ListItemsType.Content>> {
        return flow {
            emit(ApiBase.apiRequest.allSessions().toListItemsType())
        }.flowOn(Dispatchers.IO)
    }

}