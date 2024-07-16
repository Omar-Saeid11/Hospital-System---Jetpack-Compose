package com.example.hospitalsystem.presentation.viewmodels.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.domain.usecase.profileUseCase.ProfileUseCase
import com.example.hospitalsystem.presentation.mapper.profileDomainToPresentation.toPresentationModelProfile
import com.example.hospitalsystem.presentation.models.profile.PresentationModelProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        networkMonitor.isConnected.observeForever { isConnected ->
            if (isConnected && _uiState.value.error != null) {
                getProfile(UserPreferences.getUserId())
            }
        }
    }

    fun getProfile(userId: Int) {
        viewModelScope.launch {
            profileUseCase.getProfile(userId)
                .onStart {
                    _uiState.value = ProfileUiState(isLoading = true)
                }
                .catch { exception ->
                    _uiState.value = ProfileUiState(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value =
                                ProfileUiState(profile = result.data.toPresentationModelProfile())
                        }

                        is Result.Error -> {
                            _uiState.value = ProfileUiState(error = result.exception.message)
                        }

                        is Result.Loading -> {
                            _uiState.value = ProfileUiState(isLoading = true)
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}


data class ProfileUiState(
    val isLoading: Boolean = false,
    val profile: PresentationModelProfile? = null,
    val error: String? = null
)

