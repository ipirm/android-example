package az.azerconnect.domain.response

import az.azerconnect.domain.utils.ResponseStatus
import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("status")
    private val _status: ResponseStatus? = ResponseStatus.OK,
    @SerializedName("Description")
    val description: String? = ""
) {
    val status = _status ?: ResponseStatus.FAILED
}
