package az.azerconnect.domain.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String
)