package com.example.hospitalsystem.presentation.screens.common.reports

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Constant
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.presentation.screens.common.reports.composables.CommentsList
import com.example.hospitalsystem.presentation.screens.common.reports.composables.InputField
import com.example.hospitalsystem.presentation.screens.common.reports.composables.TopBar
import com.example.hospitalsystem.presentation.viewmodels.reports.ReportViewModel

@Composable
fun ReportDetailsScreen(
    navController: NavController,
    reportId: Int,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    val uiState by reportViewModel.uiState.collectAsState()
    var isVisible by remember { mutableStateOf(false) }
    var answerText by remember { mutableStateOf("") }

    val userType = UserPreferences.getUserType()

    LaunchedEffect(uiState.showReportDetails) {
        reportViewModel.showReportDetails(reportId)
        if (uiState.showReportDetails != null) {
            isVisible = true
        }
    }

    val transitionState =
        updateTransition(targetState = isVisible, label = "Report Details Transition")

    val fadeInAnimation by transitionState.animateFloat(label = "Fade In Animation") { visible ->
        if (visible) 1f else 0f
    }

    val expandAnimation by transitionState.animateDp(label = "Expand Animation") { visible ->
        if (visible) 16.dp else 0.dp
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when {
            uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            uiState.error != null -> Text(
                text = "Error: ${uiState.error}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            uiState.showReportDetails != null -> {
                val reportDetails = uiState.showReportDetails

                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(animationSpec = tween(durationMillis = 1500)) + expandIn(
                        animationSpec = tween(durationMillis = 1500)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(expandAnimation)
                            .graphicsLayer(alpha = fadeInAnimation)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            if (reportDetails != null) {
                                TopBar(
                                    reportName = reportDetails.data?.reportName ?: "Report Name",
                                    onEndClick = {
                                        reportViewModel.endReport(reportId)
                                        navController.popBackStack()
                                    }
                                )
                            }
                            if (reportDetails != null) {
                                reportDetails.data?.let { CommentsList(it) }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            if (userType == Constant.DOCTOR || userType == Constant.MANAGER) {
                                InputField(
                                    onInputChange = { answerText = it },
                                    onSendClick = {
                                        reportViewModel.answerReport(reportId, answerText)
                                        answerText = ""
                                    }
                                )
                            } else {
                                Text(
                                    text = "You do not have permission to reply to this report.",
                                    color = Color.Gray,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevDetailsReport() {
    ReportDetailsScreen(navController = NavController(LocalContext.current), 2)
}