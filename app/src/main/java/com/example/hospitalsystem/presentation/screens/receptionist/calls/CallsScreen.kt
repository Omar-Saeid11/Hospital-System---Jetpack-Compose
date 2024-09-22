package com.example.hospitalsystem.presentation.screens.receptionist.calls


import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.CallsList
import com.example.hospitalsystem.presentation.composables.LottieAnimationView
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel
import com.example.hospitalsystem.theme.Primary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallsScreen(
    navController: NavController,
    viewModel: CallsViewModel = hiltViewModel()
) {
    val allCallsState by viewModel.allCalls.collectAsState()
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    var date by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getCalls()
    }

    LaunchedEffect(date) {
        if (date.isNotEmpty()) {
            viewModel.getCallsByDate(date)
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                date = dateFormat.format(calendar.time)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnCancelListener {
                showDatePicker = false
            }
        }.show()
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
            OutlinedTextField(
                value = date.ifEmpty { "All Calls" },
                onValueChange = {},
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
                            tint = Color.White,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = 8.dp,
                                        bottomStart = 8.dp,
                                        topEnd = 6.dp,
                                        bottomEnd = 6.dp
                                    )
                                )
                                .background(Color.DarkGray)
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
                        .size(56.dp)
                        .padding(start = 1.dp)
                        .background(Primary, shape = RoundedCornerShape(12.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (allCallsState) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is Result.Success -> {
                val calls = (allCallsState as Result.Success<PresentationAllCalls>).data
                if (calls.data.isEmpty()) {
                    LottieAnimationView(R.raw.not_found)
                } else {
                    CallsList(calls) { callId ->
                        navController.navigate("${Screen.CallDetailsScreen.route}/$callId")
                    }
                }
            }

            is Result.Error -> {
                Text(
                    text = (allCallsState as Result.Error).exception.message ?: "An error occurred",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Prev() {
    CallsScreen(navController = NavController(LocalContext.current))
}