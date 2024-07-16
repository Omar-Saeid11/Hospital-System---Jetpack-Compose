package com.example.hospitalsystem.presentation.viewmodels.callsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.domain.usecase.callsUseCase.CallsUseCase
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentationAllCalls
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentationCall
import com.example.hospitalsystem.presentation.mapper.hrDomainToPresentation.toPresentationModel
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.models.calls.PresentationCall
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

    private var lastFetchedDate: String? = null

    init {
        networkMonitor.isConnected.observeForever { isConnected ->
            if (isConnected) {
                lastFetchedDate?.let { getAllCalls(it) }
                // You can also add similar calls for other functions if needed
            }
        }
    }

    fun getAllCalls(date: String) {
        lastFetchedDate = date
        viewModelScope.launch {
            callsUseCase.getAllCalls(date)
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

    override fun onCleared() {
        super.onCleared()
        networkMonitor.unregisterCallback()
    }
}
