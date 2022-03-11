package az.azerconnect.bakcell.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import asErrorDialogModel
import asUiState
import az.azerconnect.data.enums.UiState
import az.azerconnect.data.models.dto.ErrorDialogDto
import az.azerconnect.data.api.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _showErrorDialog = MutableLiveData<ErrorDialogDto?>()
    val showErrorDialog: LiveData<ErrorDialogDto?> get() = _showErrorDialog

    private val _showProgressDialog = MutableLiveData<UiState>()
    val showProgressDialog: LiveData<UiState> get() = _showProgressDialog

    protected val _uiState = MutableStateFlow(UiState.LOADING)
    val uiState = _uiState.asStateFlow()

    fun <T> executeInBackground(
        uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.LOADING),
        checkEmptyList: Boolean = false,
        hasNextRequest: Boolean = false,
        checkErrorState: Boolean = true,
        showErrorDialog: Boolean = true,
        showProgressDialog: Boolean = false,
        func: suspend () -> Resource<T>
    ) {
        if (uiState.value != UiState.LOADING)
            uiState.value = UiState.LOADING

        if (showProgressDialog)
            _showProgressDialog.value = uiState.value

        viewModelScope.launch {
            val response = func()
            val newState = response.asUiState(checkEmptyList)

            if (showErrorDialog && newState == UiState.ERROR)
                showError(response.asErrorDialogModel())

            if (hasNextRequest && newState == UiState.SUCCESS)
                return@launch

            if (checkErrorState || newState != UiState.ERROR)
                uiState.value = newState

            if (showProgressDialog)
                _showProgressDialog.value = uiState.value
        }
    }

    fun showError(model: ErrorDialogDto?) {
        _showErrorDialog.value = model
    }
}