package com.example.hospitalsystem.presentation.screens.nurse

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse
import com.example.hospitalsystem.presentation.screens.nurse.composables.CaseTypeSection
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaseDetailsNurseScreen(
    navController: NavController,
    caseId: Int,
    viewModel: CasesViewModel = hiltViewModel()
) {
    val caseState by viewModel.showCaseState.collectAsState()
    var selectedTab by remember { mutableStateOf("Case Status") }

    LaunchedEffect(caseId) {
        viewModel.showCase(caseId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Case Details", color = Color.Black)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        when (caseState) {
            is Result.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                val caseDetails =
                    (caseState as Result.Success<PresentationShowCaseResponse>).data.data
                caseDetails?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        CaseTypeSection(
                            caseDetails = it,
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it },
                            navController = navController
                        )
                    }
                }
            }

            is Result.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error loading case details", color = Color.Red)
                }
            }
        }
    }
}
