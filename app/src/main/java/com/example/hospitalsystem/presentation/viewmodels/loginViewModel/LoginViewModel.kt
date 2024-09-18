package com.example.hospitalsystem.presentation.viewmodels.loginViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.domain.usecase.loginUseCase.LoginUseCase
import com.example.hospitalsystem.presentation.mapper.loginDomainToPresentation.toPresentationModelLogin
import com.example.hospitalsystem.presentation.models.login.PresentationModelLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun login(
        email: String,
        password: String,
        deviceToken: String,
        onSuccess: (String) -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            loginUseCase.login(email, password, deviceToken)
                .onStart {
                    _loginUiState.value = LoginUiState(isLoading = true)
                }
                .catch { e ->
                    _loginUiState.value = LoginUiState(isLoading = false, error = e.message)
                    onError(e.message ?: "An unknown error occurred")
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            val presentationModelLogin = result.data.toPresentationModelLogin()
                            _loginUiState.value = LoginUiState(isLoading = false, data = presentationModelLogin)
                            saveUserData(presentationModelLogin)
                            onSuccess(presentationModelLogin.data.type)
                        }
                        is Result.Error -> {
                            _loginUiState.value = LoginUiState(isLoading = false, error = result.exception.message)
                            onError(result.exception.message ?: "An unknown error occurred")
                        }
                        is Result.Loading -> {
                            _loginUiState.value = LoginUiState(isLoading = true)
                        }
                    }
                }
        }
    }

    private fun saveUserData(presentationModelLogin: PresentationModelLogin) {
        presentationModelLogin.data.let { user ->
            UserPreferences.setUserPhone(user.mobile)
            UserPreferences.setUserType(user.type)
            UserPreferences.setUserEmail(user.email)
            UserPreferences.setUserName("${user.firstName} ${user.lastName}")
            UserPreferences.setUserId(user.id)
            UserPreferences.setUserTOKEN(user.accessToken)
            UserPreferences.setUserGender(user.gender)
            UserPreferences.setUserBirthday(user.birthday)
            UserPreferences.setUserLocation(user.address)
            UserPreferences.setUserStatus(user.status ?: "")
        }
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val data: PresentationModelLogin? = null,
    val error: String? = null
)
