package com.example.hospitalsystem.presentation.screens.common.tasks

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Utils.Companion.showMessage
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.tasksViewModel.TasksViewModel
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(navController: NavController, viewModel: TasksViewModel = hiltViewModel()) {
    var taskName by remember { mutableStateOf("") }
    var selectedEmployee by remember { mutableStateOf<PresentationUserType?>(null) }
    var description by remember { mutableStateOf("") }
    var todos by remember { mutableStateOf(mutableListOf<String>()) }
    var newTodo by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val selectedEmployeeState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<PresentationUserType>("selectedEmployee")

    selectedEmployeeState?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { employee ->
        selectedEmployee = employee
    }

    Scaffold(
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    TopAppBar(
                        title = { Text("Tasks Details") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = taskName,
                        onValueChange = { taskName = it },
                        label = { Text("Task Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = selectedEmployee?.firstName ?: "Select Employee",
                        onValueChange = { },
                        label = { Text("Select Employee") },
                        trailingIcon = {
                            IconButton(onClick = {
                                navController.navigate("${Screen.SelectionScreen.route}/0/All")
                            }) {
                                Icon(
                                    Icons.Default.ArrowForward,
                                    contentDescription = "Select Employee"
                                )
                            }
                        },
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("To do")
                    Spacer(modifier = Modifier.height(8.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        todos.forEachIndexed { index, todo ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(todo)
                                IconButton(onClick = {
                                    todos = todos.toMutableList().apply { removeAt(index) }
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Add Todo")
                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(onClick = {
                            showBottomSheet = true
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        imageVector = Icons.Default.CloudUpload,
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(64.dp),
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
                        Text("Upload Image")
                    }

                }

                Button(
                    onClick = {
                        if (taskName.isNotBlank() && selectedEmployee != null) {
                            viewModel.createTask(
                                userId = selectedEmployee!!.id,
                                taskName = taskName,
                                description = description,
                                toDos = todos
                            )
                            navController.popBackStack()
                        } else {
                            showMessage(context = context, "Fill All Fields")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.BottomCenter),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Primary)
                ) {
                    Text("Send", color = Color.White)
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = newTodo,
                                onValueChange = { newTodo = it },
                                label = { Text("To do details") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    if (newTodo.isNotBlank()) {
                                        todos = todos.toMutableList().apply { add(newTodo) }
                                        newTodo = ""
                                        showBottomSheet = false
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        0xFF00C4B4
                                    )
                                )
                            ) {
                                Text("Add", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    )
}
