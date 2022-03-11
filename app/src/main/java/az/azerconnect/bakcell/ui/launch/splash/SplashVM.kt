package az.azerconnect.bakcell.ui.launch.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import az.azerconnect.data.persistence.SessionManager
import az.azerconnect.bakcell.ui.base.BaseViewModel
import az.azerconnect.bakcell.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashVM : BaseViewModel() {

    private val _isUserLoggedIn = SingleLiveEvent<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn

    init {
        viewModelScope.launch {
            delay(1500)

            _isUserLoggedIn.value = SessionManager.loggedIn
        }
    }
}