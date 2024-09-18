package com.example.hospitalsystem.presentation.screens.common.cases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.cases.PresentationModelCases
import com.example.hospitalsystem.presentation.screens.common.cases.composables.CaseCard
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasesScreen(
    navController: NavController,
    casesViewModel: CasesViewModel = hiltViewModel()
) {
    val casesState by casesViewModel.casesState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                        Text("Cases")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(16.dp)
        ) {
            when (casesState) {
                is Result.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val cases = (casesState as Result.Success<PresentationModelCases>).data
                        items(cases.data ?: emptyList()) { case ->
                            case?.let {
                                CaseCard(
                                    name = it.patientName ?: "",
                                    date = it.createdAt ?: "",
                                    onShowDetailsClick = {
                                        if (UserPreferences.getUserType() == "nurse" || UserPreferences.getUserType() == "analysis") {
                                            navController.navigate("${Screen.CaseDetailsNurseScreen.route}/${it.id}")
                                        } else {
                                            navController.navigate("${Screen.ShowCaseScreen.route}/${it.id}")
                                        }
                                    }
                                )
                            }
                        }
                    }
                }

                is Result.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${(casesState as Result.Error).exception.message}")
                    }
                }

                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No data available")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CasesScreenPreview() {
    CasesScreen(NavController(LocalContext.current))
}
