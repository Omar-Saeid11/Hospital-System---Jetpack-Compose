package com.example.hospitalsystem.presentation.screens.hr.employee

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.hospitalsystem.R
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.data.models.employee_model.EmployeeType
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.hrViewModel.GetUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun EmployeeScreen(
    navController: NavController,
    viewModel: GetUserViewModel = hiltViewModel()
) {
    val employeesState by viewModel.userTypeStateFlow.collectAsState()
    val isLoadingState by viewModel.loadingStateFlow.collectAsState(initial = false)
    val errorState by viewModel.errorChannel.collectAsState(initial = null)

    var selectedType by remember { mutableStateOf(EmployeeType.all) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchInitiated by remember { mutableStateOf(false) }
    var isFirstLoad by remember { mutableStateOf(true) } // Track if it's the first load

    // Initiate search or data fetch when screen first opens
    LaunchedEffect(Unit) {
        viewModel.getUserType(selectedType.name, searchQuery)
        isFirstLoad = false
        isSearchInitiated = true // Set search initiated to true when screen first loads
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Employee") },
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
                containerColor = Color(0xFF22C7B8),
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

            // Show animation based on different states
            when {
                isLoadingState -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                errorState != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (isSearchInitiated) {
                            LottieAnimationView(animationResId = R.raw.not_found)
                        } else {
                            LottieAnimationView(animationResId = R.raw.search)
                        }
                    }
                }

                employeesState.isEmpty() && isSearchInitiated -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        if (isSearchInitiated) {
                            LottieAnimationView(animationResId = R.raw.search)
                        } else {
                            LottieAnimationView(animationResId = R.raw.not_found)
                        }
                    }
                }

                else -> {
                    if (!isFirstLoad) { // Only show the list once data is loaded
                        EmployeeList(employeesState)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        placeholder = { Text("Search for Employee") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
    )
}


@ExperimentalMaterialApi
@Composable
fun TypeFilter(selectedType: EmployeeType, onTypeSelected: (EmployeeType) -> Unit) {
    val types = EmployeeType.entries.toList()  // Convert the array to a list
    LazyRow {
        items(types) { type ->
            Chip(
                onClick = { onTypeSelected(type) },
                modifier = Modifier.padding(4.dp),
                border = if (selectedType == type) BorderStroke(1.dp, Color.LightGray) else null,
                colors = ChipDefaults.chipColors(
                    backgroundColor = if (selectedType == type) Color(0xFF22C7B8) else Color(
                        0xFFD3E6E9
                    )
                )
            ) {
                Text(text = type.name)
            }
        }
    }
}

@Composable
fun EmployeeList(employees: List<PresentationUserType>) {
    LazyColumn {
        items(employees) { employee ->
            EmployeeItem(employee)
        }
    }
}

@Composable
fun EmployeeItem(employee: PresentationUserType) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.ic_user),
                contentDescription = employee.firstName,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Icon(
                Icons.Default.Circle,
                contentDescription = "Available",
                tint = Color.Green,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(12.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(employee.firstName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(employee.type, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun LottieAnimationView(animationResId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(400.dp)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewEmployeeScreen() {
    EmployeeScreen(navController = NavController(LocalContext.current))
}