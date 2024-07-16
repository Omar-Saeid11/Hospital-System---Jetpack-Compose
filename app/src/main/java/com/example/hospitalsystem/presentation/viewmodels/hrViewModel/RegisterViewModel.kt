package com.example.hospitalsystem.presentation.viewmodels.hrViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrRegisterUseCase
import com.example.hospitalsystem.presentation.mapper.hrDomainToPresentation.toPresentationModel
import com.example.hospitalsystem.presentation.models.hr.register.PresentationModelRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val hrRegisterUseCase: HrRegisterUseCase) :
    ViewModel() {

    private val _registerState = MutableStateFlow<Result<PresentationModelRegister>>(Result.Loading)
    val registerState: StateFlow<Result<PresentationModelRegister>> = _registerState.asStateFlow()

    fun register(data: RegisterData) {
        viewModelScope.launch {
            hrRegisterUseCase.register(data)
                .map { result ->
                    when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationModel())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
                .collect { mappedResult ->
                    _registerState.value = mappedResult
                }
        }
    }
}
