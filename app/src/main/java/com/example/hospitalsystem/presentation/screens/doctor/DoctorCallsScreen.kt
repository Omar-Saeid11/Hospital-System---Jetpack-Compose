package com.example.hospitalsystem.presentation.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.composables.LottieAnimationView
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.screens.doctor.composables.CallCard
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorCallsScreen(navController: NavController, viewModel: CallsViewModel = hiltViewModel()) {
    val callItemsState by viewModel.allCalls.collectAsState()
    val acceptOrCancelCallState by viewModel.acceptOrCancelCall.collectAsState()

    LaunchedEffect(acceptOrCancelCallState) {
        if (acceptOrCancelCallState is Result.Success) {
            viewModel.getCalls()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getCalls()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Calls")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                when (callItemsState) {
                    is Result.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }

                    is Result.Success -> {
                        val callItems =
                            (callItemsState as Result.Success<PresentationAllCalls>).data.data
                        if (callItems.isEmpty()) {
                            LottieAnimationView(R.raw.not_found)
                        } else {
                            LazyColumn {
                                items(callItems) { callItem ->
                                    CallCard(
                                        name = callItem.patientName,
                                        date = callItem.createdAt,
                                        onAccept = {
                                            viewModel.acceptOrCancelCall(
                                                callItem.id,
                                                "accepted"
                                            )
                                        },
                                        onCancel = {
                                            viewModel.acceptOrCancelCall(
                                                callItem.id,
                                                "canceled"
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                    }

                    is Result.Error -> {
                        Text(
                            text = "Error loading calls",
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    )
}
