package az.azerconnect.data.api.services

import az.azerconnect.data.models.request.LoginRequest
import az.azerconnect.domain.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login/register")
    suspend fun register(@Body request: LoginRequest?): UserResponse
}
