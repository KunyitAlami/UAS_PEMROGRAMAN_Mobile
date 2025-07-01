package com.example.uas_mobile.domain.models

sealed class Resource<t>{
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val exception: Throwable? = null) : Resource<T>()
    data class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}

