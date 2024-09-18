package com.example.hospitalsystem.presentation.screens.common.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationModelShowTask
import com.example.hospitalsystem.presentation.viewmodels.tasksViewModel.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    navController: NavController,
    taskId: Int,
    viewModel: TasksViewModel = hiltViewModel()
) {

    var isExecutionEnabled by remember { mutableStateOf(true) }

    var refreshKey by remember { mutableIntStateOf(taskId) }

    LaunchedEffect(key1 = refreshKey) {
        viewModel.showTask(taskId)
    }

    val showTaskState by viewModel.showTaskState.collectAsState()

    when (showTaskState) {
        is Result.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {
            val task = (showTaskState as Result.Success<PresentationModelShowTask>).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                TopAppBar(
                    title = { Text("Tasks Details") },
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
                TaskDetailsContent(
                    task = task,
                    taskId = taskId,
                    isExecutionEnabled = isExecutionEnabled,
                    onExecuteClick = { taskId, note ->
                        viewModel.executeTask(taskId, note)
                        isExecutionEnabled = false
                        refreshKey += 1
                    }
                )
            }
        }

        is Result.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error loading task details")
            }
        }
    }
}