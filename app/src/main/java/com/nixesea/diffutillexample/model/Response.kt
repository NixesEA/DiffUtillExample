package com.nixesea.diffutillexample.model

sealed class Response {
    class Success(val contentList: ArrayList<Content>) : Response()
    class Error(val message: String) : Response()
}