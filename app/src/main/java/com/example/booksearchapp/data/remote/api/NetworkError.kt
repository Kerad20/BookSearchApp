package com.example.booksearchapp.data.remote.api


sealed interface NetworkError {

    data object NoInternet : NetworkError

    data object Timeout : NetworkError

    data object Serialization : NetworkError

    data object Unauthorized : NetworkError

    data object Forbidden : NetworkError

    data object NotFound : NetworkError

    data object ServerError : NetworkError

    data object Unknown : NetworkError
}

fun NetworkError.toMessage(): String {

    return when (this) {

        NetworkError.NoInternet ->
            "No internet connection"

        NetworkError.Timeout ->
            "Request timeout"

        NetworkError.NotFound ->
            "Book not found"

        NetworkError.ServerError ->
            "Server error"

        NetworkError.Unauthorized ->
            "Not authorized"

        NetworkError.Serialization ->
            "Serializing data error"

        NetworkError.Forbidden ->
            "Forbidden"

        else ->
            "Unknown error"
    }
}