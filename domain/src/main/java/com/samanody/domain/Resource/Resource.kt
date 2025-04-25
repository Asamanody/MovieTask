package com.samanody.domain.Resource

import java.io.Serializable

sealed class Resource<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(data: T? = null, message: String): Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class Exception<T>(message: String, data: T? = null): Resource<T>(data, message), Serializable
    class Empty<T>(): Resource<T>(), Serializable
}
