package az.azerconnect.data.api.utils

import az.azerconnect.domain.utils.ResponseStatus


sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()

    data class Error(
        val message: String? = "",
        val statusEnum: ResponseStatus = ResponseStatus.FAILED
    ) : Resource<Nothing>()
}

inline fun <T : Any> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

inline fun <T : Any> Resource<T>.onError(action: (message: String?, statusEnum: ResponseStatus?) -> Unit): Resource<T> {
    if (this is Resource.Error) action(message, statusEnum)
    return this
}