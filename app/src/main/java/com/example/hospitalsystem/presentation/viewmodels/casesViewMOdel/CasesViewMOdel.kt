package com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.usecase.casesUseCase.CasesUseCase
import com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation.toPresentationCall
import com.example.hospitalsystem.presentation.mapper.casesDomainToPresentation.toPresentationModelCases
import com.example.hospitalsystem.presentation.mapper.casesDomainToPresentation.toPresentationShowCaseResponse
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationModelCreateReport
import com.example.hospitalsystem.presentation.models.calls.PresentationCall
import com.example.hospitalsystem.presentation.models.cases.PresentationModelCases
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse
import com.example.hospitalsystem.presentation.models.report.createReport.PresentationModelCreateReport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesViewModel @Inject constructor(private val casesUseCase: CasesUseCase) : ViewModel() {

    private val _casesState = MutableStateFlow<Result<PresentationModelCases>?>(null)
    val casesState: StateFlow<Result<PresentationModelCases>?> = _casesState

    private val _showCaseState =
        MutableStateFlow<Result<PresentationShowCaseResponse>>(Result.Loading)
    val showCaseState: StateFlow<Result<PresentationShowCaseResponse>> = _showCaseState

    private val _makeRequestState = MutableStateFlow<Result<PresentationCall>?>(null)
    val makeRequestState: StateFlow<Result<PresentationCall>?> = _makeRequestState

    private val _addMeasurementState =
        MutableStateFlow<Result<PresentationModelCreateReport>>(Result.Loading)
    val addMeasurementState: StateFlow<Result<PresentationModelCreateReport>> = _addMeasurementState

    init {
        fetchCases()
    }

    private fun fetchCases() {
        viewModelScope.launch {
            casesUseCase.getAllCases()
                .catch { e -> _casesState.value = Result.Error(e) }
                .collect { result ->
                    _casesState.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationModelCases())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun showCase(id: Int) {
        viewModelScope.launch {
            casesUseCase.showCase(id)
                .catch { e -> _showCaseState.value = Result.Error(e) }
                .collect { result ->
                    _showCaseState.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationShowCaseResponse())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ) {
        viewModelScope.launch {
            casesUseCase.makeRequest(callId, userId, note, types)
                .catch { e -> _makeRequestState.value = Result.Error(e) }
                .collect { result ->
                    _makeRequestState.value = when (result) {
                        is Result.Success -> Result.Success(result.data.toPresentationCall())
                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun addMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        note: String,
        status: String
    ) {
        viewModelScope.launch {
            _addMeasurementState.value = Result.Loading
            casesUseCase.addMeasurement(
                caseId, bloodPressure, sugarAnalysis, tempreture, fluidBalance,
                respiratoryRate, heartRate, note, status
            )
                .catch { e -> _addMeasurementState.value = Result.Error(e) }
                .collect { result ->
                    _addMeasurementState.value = when (result) {
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
