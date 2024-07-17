package com.example.hospitalsystem.presentation.screens.receptionist.calls


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.models.calls.PresentationCallData
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallsScreen(
    navController: NavController,
    viewModel: CallsViewModel = hiltViewModel()
) {
    val allCalls by viewModel.allCalls.collectAsState()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    var date by remember { mutableStateOf(dateFormat.format(calendar.time)) }
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllCalls(date)
    }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                date = dateFormat.format(calendar.time)
                showDatePicker = false
                viewModel.getAllCalls(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
            title = {
                Text(
                    text = "Calls",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = date,
                onValueChange = { date = it },
                modifier = Modifier.weight(1f),
                readOnly = true,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Cyan,
                    unfocusedIndicatorColor = Color.Gray
                ),
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Select Date",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
            IconButton(onClick = { navController.navigate(Screen.CreateCallScreen.route) }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF00ACC1), shape = RoundedCornerShape(12.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (allCalls) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is Result.Success -> {
                val calls = (allCalls as Result.Success<PresentationAllCalls>).data
                CallsList(calls) { callId ->
                    navController.navigate("${Screen.CallDetailsScreen.route}/$callId")
                }
            }

            is Result.Error -> {
                Text(
                    text = (allCalls as Result.Error).exception.message ?: "An error occurred",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun CallsList(calls: PresentationAllCalls, onCallClick: (Int) -> Unit) {
    LazyColumn {
        items(calls.data) { call ->
            CallRow(call) { onCallClick(call.id) }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CallRow(call: PresentationCallData, onCallClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onCallClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Contact",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                            .background(
                                Color(0xFF00ACC1),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(call.patientName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Date",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                            .background(
                                Color(0xFF00ACC1),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(call.createdAt, color = Color.Gray, fontSize = 14.sp)
                }
            }
            Icon(
                imageVector = if (call.status == "accept_doctor") Icons.Filled.CheckCircle else Icons.Filled.AccessTime,
                contentDescription = if (call.status == "accept_doctor") "Completed" else "Missed",
                tint = if (call.status == "accept_doctor") Color.Green else Color(0xFFFFA000),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    CallsScreen(navController = NavController(LocalContext.current))
}