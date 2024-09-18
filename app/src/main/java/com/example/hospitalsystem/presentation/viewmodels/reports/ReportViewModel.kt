package com.example.hospitalsystem.presentation.viewmodels.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.NetworkMonitor
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import com.example.hospitalsystem.domain.usecase.reportUseCase.ReportUseCase
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationModelCreateReport
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationModelShowReport
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationReportResponse
import com.example.hospitalsystem.presentation.models.report.PresentationReportResponse
import com.example.hospitalsystem.presentation.models.report.createReport.PresentationModelCreateReport
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationModelShowReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
        observeNetworkConnectivity()
    }

    private fun observeNetworkConnectivity() {
        networkMonitor.isConnected.observeForever { isConnected ->
            if (isConnected && _uiState.value.error != null) {
                refreshData()
            }
        }
    }

    private fun refreshData() {
        getReports()
    }

    fun getReports() {
        viewModelScope.launch {
            executeUseCaseCall(
                call = { reportUseCase.getReports() },
                onSuccess = { reportResponse ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        reportResponse = reportResponse.toPresentationReportResponse(),
                        error = null
                    )
                }
            )
        }
    }

    fun getReportsByDate(date: String) {
        viewModelScope.launch {
            executeUseCaseCall(
                call = { reportUseCase.getReportsByDate(date) },
                onSuccess = { reportResponse ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        reportResponse = reportResponse.toPresentationReportResponse(),
                        error = null
                    )
                }
            )
        }
    }

    fun createReport(name: String, description: String) {
        viewModelScope.launch {
            reportUseCase.createReport(name, description)
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                createReportResponse = result.data.toPresentationModelCreateReport(),
                                error = null
                            )
                        }

                        is Result.Error -> {
                            _uiState.value = _uiState.value.copy(error = result.exception.message)
                        }

                        is Result.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

    fun showReportDetails(id: Int) {
        viewModelScope.launch {
            executeShowReportDetailsCall(
                call = { reportUseCase.showReportDetails(id) },
                onSuccess = { reportDetails ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        showReportDetails = reportDetails.toPresentationModelShowReport(),
                        error = null
                    )
                }
            )
        }
    }
    fun answerReport(id: Int, answer: String) {
        viewModelScope.launch {
            reportUseCase.answerReport(id, answer)
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = null
                            )
                        }
                        is Result.Error -> {
                            _uiState.value = _uiState.value.copy(error = result.exception.message)
                        }
                        is Result.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }
    fun endReport(id: Int) {
        viewModelScope.launch {
            reportUseCase.endReport(id)
                .onStart { _uiState.value = _uiState.value.copy(isLoading = true) }
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(error = exception.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = null
                            )
                        }

                        is Result.Error -> {
                            _uiState.value = _uiState.value.copy(error = result.exception.message)
                        }

                        is Result.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

    private suspend fun executeUseCaseCall(
        call: suspend () -> Flow<Result<DomainReportResponse>>,
        onSuccess: (DomainReportResponse) -> Unit
    ) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        try {
            call().collect { result ->
                when (result) {
                    is Result.Success -> onSuccess(result.data)
                    is Result.Error -> _uiState.value =
                        _uiState.value.copy(error = result.exception.message)

                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(error = e.message)
        }
    }

    private suspend fun executeShowReportDetailsCall(
        call: suspend () -> Flow<Result<DomainModelShowReport>>,
        onSuccess: (DomainModelShowReport) -> Unit
    ) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        try {
            call().collect { result ->
                when (result) {
                    is Result.Success -> onSuccess(result.data)
                    is Result.Error -> _uiState.value =
                        _uiState.value.copy(error = result.exception.message)

                    is Result.Loading -> _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(error = e.message)
        }
    }
}


data class ReportUiState(
    val isLoading: Boolean = false,
    val reportResponse: PresentationReportResponse? = null,
    val createReportResponse: PresentationModelCreateReport? = null,
    val showReportDetails: PresentationModelShowReport? = null,
    val error: String? = null
)
