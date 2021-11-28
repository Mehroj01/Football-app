package com.example.footballapp.utils

import com.example.footballapp.model_extra.MainModel

sealed class MainModelResource {
    object Loading : MainModelResource()
    data class Success(val body: MainModel?) : MainModelResource()
    data class Error(var message: String) : MainModelResource()
}