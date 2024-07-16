package com.example.hospitalsystem.presentation.viewmodels.hrViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrGetUserTypeUseCase
import com.example.hospitalsystem.presentation.mapper.hrDomainToPresentation.toPresentationModel
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetUserViewModel @Inject constructor(private val hrGetUserTypeUseCase: HrGetUserTypeUseCase) :
    ViewModel() {

    private val _userTypeStateFlow = MutableStateFlow<List<PresentationUserType>>(emptyList())
    val userTypeStateFlow get() = _userTypeStateFlow

    private val _loadingStateFlow = Channel<Boolean>()
    val loadingStateFlow = _loadingStateFlow.receiveAsFlow()

    private val _errorChannel = Channel<String?>()
    val errorChannel = _errorChannel.receiveAsFlow()

    fun getUserType(type: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                _loadingStateFlow.send(true)
            }
            hrGetUserTypeUseCase.getUserType(type, name).collect { result ->
                if (result is Result.Success) {
                    _userTypeStateFlow.emit(result.data.toPresentationModel().data)
                } else if (result is Result.Error) {
                    _errorChannel.send(result.exception.message)
                }
                launch {
                    _loadingStateFlow.send(false)
                }
            }
        }
    }
}

