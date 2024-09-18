package com.example.hospitalsystem.presentation.screens.manager


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReportScreen(
    onBackClick: () -> Unit,
    onUploadClick: () -> Unit,
    onCreateReportClick: () -> Unit,
    navController: NavController,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedEmployeeName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        val selectedEmployeeType = savedStateHandle?.get<PresentationUserType>("selectedNurse")
        selectedEmployeeName =
            selectedEmployeeType?.firstName
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Report") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = { Text("Report Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDialog = true }
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = selectedEmployeeName ?: "Select Employee",
                            style = TextStyle(color = Color.Black)
                        )
                    }

                    OutlinedTextField(
                        value = "",
                        onValueChange = {  },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                            .clickable { onUploadClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.CloudDownload,
                                contentDescription = "Upload Icon",
                                modifier = Modifier.size(80.dp),
                                tint = Color(0xFF00BFA5)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(onClick = { onUploadClick() }) {
                                Icon(Icons.Default.Add, contentDescription = "Upload Image")
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Upload Image")
                            }
                        }
                    }
                }

                Button(
                    onClick = { onCreateReportClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF00BFA5))
                ) {
                    Text("Create Report", color = Color.White)
                }
            }

            if (showDialog) {
                EmployeeTypeSelectionDialog(
                    onDismiss = { showDialog = false },
                    onTypeSelected = { type ->
                        navController.navigate("${Screen.SelectionScreen.route}/${0}/$type")
                    }
                )
            }
        }
    )
}


@Composable
fun EmployeeTypeSelectionDialog(
    onDismiss: () -> Unit,
    onTypeSelected: (String) -> Unit
) {
    val types = listOf("doctor", "hr", "receptionist", "analysis", "manager", "nurse")

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Select Employee Type") },
        text = {
            Column {
                types.forEach { type ->
                    TextButton(
                        onClick = {
                            onTypeSelected(type)
                            onDismiss()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = type.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                        })
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
