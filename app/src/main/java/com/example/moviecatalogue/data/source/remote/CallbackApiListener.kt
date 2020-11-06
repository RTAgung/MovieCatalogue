package com.example.moviecatalogue.data.source.remote

interface CallbackApiListener<T> {
    fun onSuccess(response: T?)
    fun onFailed(message: String?)
}