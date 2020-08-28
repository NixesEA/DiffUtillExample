package com.nixesea.diffutillexample.network

object ApiBase {

    val apiRequest: IVoteApi
        get() = RetrofitClient.getClient("https://raw.githubusercontent.com/").create(IVoteApi::class.java)

}