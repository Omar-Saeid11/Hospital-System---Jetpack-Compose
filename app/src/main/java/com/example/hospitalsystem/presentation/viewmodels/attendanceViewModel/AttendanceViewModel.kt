package com.example.hospitalsystem.presentation.viewmodels.attendanceViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.usecase.attendanceUseCase.AttendanceUseCase
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationModelCreateReport
import com.example.hospitalsystem.presentation.models.report.createReport.PresentationModelCreateReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(private val attendanceUseCase: AttendanceUseCase) :
    ViewModel() {

    private val _attendanceState =
        MutableStateFlow<Result<PresentationModelCreateReport>>(Result.Loading)
    val attendanceState: StateFlow<Result<PresentationModelCreateReport>> = _attendanceState

    fun attendance(status: String) {
        viewModelScope.launch {
            _attendanceState.value = Result.Loading
            attendanceUseCase.attendance(status)
                .catch { e -> _attendanceState.value = Result.Error(e) }
                .collect { result ->
                    _attendanceState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentationModelCreateReport())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }
}
