package com.example.booksearchapp.data.remote.api

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ApiResult<T> {

    return try {

        val response = execute()

        when (response.status) {

            HttpStatusCode.OK -> {

                ApiResult.Success(
                    response.body<T>()
                )
            }

            HttpStatusCode.Unauthorized -> {
                ApiResult.Error(
                    NetworkError.Unauthorized
                )
            }

            HttpStatusCode.Forbidden -> {
                ApiResult.Error(
                    NetworkError.Forbidden
                )
            }

            HttpStatusCode.NotFound -> {
                ApiResult.Error(
                    NetworkError.NotFound
                )
            }

            HttpStatusCode.InternalServerError -> {

                ApiResult.Error(
                    NetworkError.ServerError
                )
            }

            else -> {

                ApiResult.Error(
                    NetworkError.Unknown
                )
            }
        }

    } catch (_: IOException) {

        ApiResult.Error(
            NetworkError.NoInternet
        )

    } catch (_: TimeoutCancellationException) {

        ApiResult.Error(
            NetworkError.Timeout
        )

    } catch (_: JsonConvertException) {

        ApiResult.Error(
            NetworkError.Serialization
        )

    } catch (_: Exception) {

        ApiResult.Error(
            NetworkError.Unknown
        )
    }
}