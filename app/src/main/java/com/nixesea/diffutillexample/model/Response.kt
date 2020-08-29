package com.nixesea.diffutillexample.model

sealed class Response {
    class Success(val contentList: ArrayList<ListItemsType>) : Response()
    class Error(val message: String) : Response()
}