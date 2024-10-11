package com.example.domain.error

sealed interface Error

sealed interface DataError : Error {
    enum class NetworkError : DataError {
        NO_INTERNET,
        UNKNOWN
    }
}