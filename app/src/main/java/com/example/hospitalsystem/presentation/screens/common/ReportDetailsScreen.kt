package com.example.hospitalsystem.presentation.screens.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.Utils.Companion.showMessage
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationReportData
import com.example.hospitalsystem.presentation.viewmodels.reports.ReportViewModel

@Composable
fun ReportDetailsScreen(
    navController: NavController,
    reportId: Int,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    val uiState by reportViewModel.uiState.collectAsState()
    var isVisible by remember { mutableStateOf(false) }

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
                                TopBar(reportDetails.data?.reportName ?: "Report Name")
                            }
                            if (reportDetails != null) {
                                reportDetails.data?.let { CommentsList(it) }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            InputField()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TopBar(reportName: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            readOnly = true,
            value = reportName,
            onValueChange = {},
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
        )
        Button(
            onClick = { showMessage(context = context, "This feature will be added soon!.") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .height(56.dp)
        ) {
            Text("End", color = Color.White)
        }
    }
}

@Composable
fun CommentsList(reportData: PresentationReportData) {
    Column {
        reportData.let { data ->
            CommentItem(
                name = data.user?.firstName ?: "",
                role = "Specialist - ${data.user?.specialist}",
                date = data.createdAt ?: "",
                comment = data.description ?: ""
            )

            if (data.manger?.firstName.isNullOrEmpty() && data.manger?.lastName.isNullOrEmpty()) {
                Text(
                    text = "Manager has not answered on this report until now",
                    modifier = Modifier.padding(8.dp),
                    color = Color.Gray
                )
            } else {
                CommentItem(
                    name = "${data.manger?.firstName} ${data.manger?.lastName}",
                    role = "Specialist - ${data.manger?.specialist}",
                    date = data.manger?.updatedAt ?: "",
                    comment = data.answer ?: ""
                )
            }
        }
    }
}


@Composable
fun CommentItem(
    name: String,
    role: String,
    date: String,
    comment: String,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(name, fontWeight = FontWeight.Bold)
                Text(role, color = Color(0xFF22C7B8), fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(date, color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(comment)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun InputField() {
    var input by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (input.isEmpty()) {
                        Text("Type your Reply", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_upload),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        Button(
            onClick = { showMessage(context = context, "This feature will be added soon!.") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF22C7B8))
        ) {
            Text("Send", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevDetailsReport() {
    ReportDetailsScreen(navController = NavController(LocalContext.current), 2)
}