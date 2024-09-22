package com.example.hospitalsystem.presentation.screens.hr.employee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.data.models.employee_model.EmployeeType
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.EmployeeList
import com.example.hospitalsystem.presentation.composables.LottieAnimationView
import com.example.hospitalsystem.presentation.composables.SearchBar
import com.example.hospitalsystem.presentation.composables.TypeFilter
import com.example.hospitalsystem.presentation.viewmodels.hrViewModel.GetUserViewModel
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun EmployeeScreen(
    navController: NavController,
    viewModel: GetUserViewModel = hiltViewModel()
) {
    val employeesState by viewModel.userTypeStateFlow.collectAsState()
    val isLoadingState by viewModel.loadingStateFlow.collectAsState(initial = false)
    val errorState by viewModel.errorChannel.collectAsState(initial = null)

    var selectedType by remember { mutableStateOf(EmployeeType.All) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchInitiated by remember { mutableStateOf(false) }
    var isFirstLoad by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.getUserType(selectedType.name, searchQuery)
        isFirstLoad = false
        isSearchInitiated = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), Alignment.TopCenter) {
                        Text("Employee")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddEmployeeScreen.route) },
                shape = RoundedCornerShape(32.dp),
                containerColor = Primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Employee")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SearchBar(searchQuery) { query ->
                searchQuery = query
                viewModel.getUserType(selectedType.name, query)
                isSearchInitiated = true
            }
            TypeFilter(selectedType) { type ->
                selectedType = type
                viewModel.getUserType(type.name, searchQuery)
                isSearchInitiated = true
            }

            when {
                isLoadingState -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                errorState != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LottieAnimationView(animationResId = R.raw.not_found)
                    }
                }

                employeesState.isEmpty() && searchQuery.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LottieAnimationView(animationResId = R.raw.search)
                    }
                }

                employeesState.isEmpty() && isSearchInitiated -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LottieAnimationView(animationResId = R.raw.not_found)
                    }
                }

                else -> {
                    if (!isFirstLoad) {
                        EmployeeList(employeesState) { employeeId ->
                            navController.navigate("${Screen.ProfileScreen.route}/$employeeId")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmployeeScreen() {
    EmployeeScreen(navController = NavController(LocalContext.current))
}