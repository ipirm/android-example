package az.azerconnect.data.repository

import az.azerconnect.data.api.services.AuthApiService
import az.azerconnect.data.api.utils.ResponseHandler
import az.azerconnect.data.models.request.LoginRequest

class AuthRepo(  private val api: AuthApiService): BaseRepo() {
    suspend fun login(request: LoginRequest) = callApi {
        val response = api.register(request)
        ResponseHandler.handleSuccess(response, response)
    }
}