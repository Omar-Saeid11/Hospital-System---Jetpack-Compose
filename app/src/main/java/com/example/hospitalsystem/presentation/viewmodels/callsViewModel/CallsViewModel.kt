package com.example.hospitalsystem.presentation.viewmodels.callsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.domain.usecase.callsUseCase.CallsUseCase
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentation
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentationAllCalls
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentationCall
import com.example.hospitalsystem.presentation.mapper.hrDomainToPresentation.toPresentationModel
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.models.calls.PresentationCall
import com.example.hospitalsystem.presentation.models.calls.PresentationLogout
import com.example.hospitalsystem.presentation.models.calls.showCall.PresentationCallData
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationModelUserType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallsViewModel @Inject constructor(
    private val callsUseCase: CallsUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _allCalls = MutableStateFlow<Result<PresentationAllCalls>>(Result.Loading)
    val allCalls: StateFlow<Result<PresentationAllCalls>> = _allCalls

    private val _createCall = MutableStateFlow<Result<PresentationCall>>(Result.Loading)
    val createCall: StateFlow<Result<PresentationCall>> = _createCall

    private val _doctors = MutableStateFlow<Result<PresentationModelUserType>>(Result.Loading)
    val doctors: StateFlow<Result<PresentationModelUserType>> = _doctors

    private val _callDetails = MutableStateFlow<Result<PresentationCallData>>(Result.Loading)
    val callDetails: StateFlow<Result<PresentationCallData>> = _callDetails

    private val _logoutState = MutableStateFlow<Result<PresentationLogout>>(Result.Loading)
    val logoutState: StateFlow<Result<PresentationLogout>> = _logoutState

    private val _acceptOrCancelCall = MutableStateFlow<Result<PresentationCall>>(Result.Loading)
    val acceptOrCancelCall: StateFlow<Result<PresentationCall>> = _acceptOrCancelCall

    private val _addNurseState = MutableStateFlow<Result<PresentationCall>>(Result.Loading)
    val addNurseState: StateFlow<Result<PresentationCall>> = _addNurseState

    init {
        networkMonitor.isConnected.observeForever { isConnected ->
            if (isConnected && _allCalls.value is Result.Error) {
                getCalls()
            }
        }
        networkMonitor.wasDisconnected.observeForever { wasDisconnected ->
            if (wasDisconnected && _allCalls.value is Result.Error) {
                getCalls()
            }
        }
    }

    fun getCalls() {
        viewModelScope.launch {
            callsUseCase.getCalls()
                .catch { e -> _allCalls.value = Result.Error(e) }
                .collect { result ->
                    _allCalls.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationAllCalls())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun getCallsByDate(date: String) {
        viewModelScope.launch {
            callsUseCase.getCallsByDate(date)
                .catch { e -> _allCalls.value = Result.Error(e) }
                .collect { result ->
                    _allCalls.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationAllCalls())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun createCall(call: CallData) {
        viewModelScope.launch {
            callsUseCase.createCall(call)
                .catch { e -> _createCall.value = Result.Error(e) }
                .collect { result ->
                    _createCall.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationCall())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun getDoctors(type: String, name: String) {
        viewModelScope.launch {
            callsUseCase.getDoctors(type, name)
                .catch { e -> _doctors.value = Result.Error(e) }
                .collect { result ->
                    _doctors.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationModel())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun showCall(id: Int) {
        viewModelScope.launch {
            callsUseCase.showCall(id)
                .catch { e -> _callDetails.value = Result.Error(e) }
                .collect { result ->
                    _callDetails.value = when (result) {
                        is Result.Success -> result.data.data?.let { Result.Success(it.toPresentation()) }!!
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun logout(id: Int) {
        viewModelScope.launch {
            callsUseCase.logout(id)
                .catch { e -> _logoutState.value = Result.Error(e) }
                .collect { result ->
                    _logoutState.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentation())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun acceptOrCancelCall(id: Int, status: String) {
        viewModelScope.launch {
            callsUseCase.acceptOrCancelCall(id, status)
                .catch { e -> _acceptOrCancelCall.value = Result.Error(e) }
                .collect { result ->
                    _acceptOrCancelCall.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationCall())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun addNurse(callId: Int, nurseId: Int) {
        viewModelScope.launch {
            callsUseCase.addNurse(callId, nurseId)
                .catch { e -> _acceptOrCancelCall.value = Result.Error(e) }
                .collect { result ->
                    _addNurseState.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationCall())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}
