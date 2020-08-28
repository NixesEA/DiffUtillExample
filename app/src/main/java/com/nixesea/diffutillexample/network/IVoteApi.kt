package com.nixesea.diffutillexample.network

import com.nixesea.diffutillexample.model.Response
import retrofit2.http.GET

interface IVoteApi {

    @GET("iMofas/ios-android-test/master/0777.json")
    suspend fun allSessions(): ArrayList<Response>

}
