package az.azerconnect.domain.response

import az.azerconnect.domain.models.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: UserModel
) : BaseResponse()