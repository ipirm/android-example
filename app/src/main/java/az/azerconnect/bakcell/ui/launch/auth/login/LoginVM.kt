package az.azerconnect.bakcell.ui.launch.auth.login

import az.azerconnect.bakcell.ui.base.BaseViewModel
import az.azerconnect.bakcell.utils.CombinedStateFlow
import az.azerconnect.bakcell.utils.extensions.isValidEmail
import az.azerconnect.bakcell.utils.extensions.isValidPassword
import az.azerconnect.data.api.utils.onSuccess
import az.azerconnect.data.enums.UiState
import az.azerconnect.data.models.request.LoginRequest
import az.azerconnect.data.repository.AuthRepo
import kotlinx.coroutines.flow.MutableStateFlow

class LoginVM(private val repo: AuthRepo) : BaseViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    val loginEnable = CombinedStateFlow(email, password) {
        email.value.isValidEmail() && password.value.isValidPassword()
    }

    init {
        _uiState.value = UiState.EMPTY
    }

    fun login() = executeInBackground(_uiState) {
        val request = LoginRequest(
            email = email.value,
            password = password.value.uppercase()
        )

        repo.login(request).onSuccess {
            it.data.firstName
        }
    }

}