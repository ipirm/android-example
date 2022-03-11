package az.azerconnect.data.api.utils

import az.azerconnect.domain.response.BaseResponse
import az.azerconnect.domain.utils.ResponseStatus
import timber.log.Timber

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

object ResponseHandler {

    fun <T : Any> handleSuccess(baseResponse: BaseResponse, data: T?): Resource<T> {
        return if (isSuccessful(baseResponse) && data != null)
            Resource.Success(data)
        else if (data == null)
            Resource.Error(baseResponse.description, ResponseStatus.NULL_DATA)
        else
            Resource.Error(baseResponse.description, baseResponse.status)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        Timber.e(e.toString())

        return Resource.Error(e.message)
    }

    private fun isSuccessful(baseResponse: BaseResponse): Boolean {
        return baseResponse.status == ResponseStatus.OK
    }
}
