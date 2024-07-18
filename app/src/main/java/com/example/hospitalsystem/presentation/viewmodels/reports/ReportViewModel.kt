package com.example.hospitalsystem.presentation.viewmodels.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.usecase.reportUseCase.ReportUseCase
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationReportResponse
import com.example.hospitalsystem.presentation.models.report.PresentationReportResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val reportUseCase: ReportUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    init {
        networkMonitor.isConnected.observeForever { isConnected ->
            if (isConnected && _uiState.value.error != null) {
                getReports()
            }
        }
    }

    fun getReports() {
        viewModelScope.launch {
            reportUseCase.getReports()
                .onStart { _uiState.value = ReportUiState(isLoading = true) }
                .catch { exception ->
                    _uiState.value = ReportUiState(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value =
                                ReportUiState(reportResponse = result.data.toPresentationReportResponse())
                        }

                        is Result.Error -> {
                            _uiState.value = ReportUiState(error = result.exception.message)
                        }

                        is Result.Loading -> {
                            _uiState.value = ReportUiState(isLoading = true)
                        }
                    }
                }
        }
    }

    fun getReportsByDate(date: String) {
        viewModelScope.launch {
            reportUseCase.getReportsByDate(date)
                .onStart { _uiState.value = ReportUiState(isLoading = true) }
                .catch { exception ->
                    _uiState.value = ReportUiState(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value =
                                ReportUiState(reportResponse = result.data.toPresentationReportResponse())
                        }

                        is Result.Error -> {
                            _uiState.value = ReportUiState(error = result.exception.message)
                        }

                        is Result.Loading -> {
                            _uiState.value = ReportUiState(isLoading = true)
                        }
                    }
                }
        }
    }
}

data class ReportUiState(
    val isLoading: Boolean = false,
    val reportResponse: PresentationReportResponse? = null,
    val error: String? = null
)
