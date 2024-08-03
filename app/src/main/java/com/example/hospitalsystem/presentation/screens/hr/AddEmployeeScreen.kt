package com.example.hospitalsystem.presentation.screens.hr

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalPhone
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Transgender
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.presentation.composables.CustomDropdownMenu
import com.example.hospitalsystem.presentation.composables.TextInput
import com.example.hospitalsystem.presentation.composables.TopBottomGradient
import com.example.hospitalsystem.presentation.viewmodels.hrViewModel.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun AddEmployeeScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var errorMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val registerState by registerViewModel.registerState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AddEmployeeContent { registerData ->
            registerViewModel.register(registerData)
        }

        when (registerState) {
            is Result.Loading -> {

            }

            is Result.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.EmployeeScreen.route)
                }
            }

            is Result.Error -> {
                LaunchedEffect(Unit) {
                    errorMessage =
                        (registerState as Result.Error).exception.message ?: "Unknown error"
                }
            }
        }

        LaunchedEffect(errorMessage) {
            if (errorMessage.isNotEmpty()) {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = "Dismiss"
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) { data ->
            Snackbar(
                backgroundColor = Color.Red,
                contentColor = Color.White,
                action = {
                    TextButton(
                        onClick = {
                            data.performAction()
                            errorMessage = "" // Clear error on action
                        },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                    ) {
                        Text(data.actionLabel ?: "Dismiss")
                    }
                }
            ) {
                Text(data.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeContent(
    onClickCreate: (RegisterData) -> Unit
) {
    val genderOptions = listOf("Gender", "Male", "Female")
    val specialistOptions = listOf(
        "Specialist", "doctor", "nurse", "receptionist", "manager", "laboratory physician", "hr"
    )
    val statusOptions = listOf("Status", "Single", "Married")

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf(genderOptions[0]) }
    var selectedSpecialist by remember { mutableStateOf(specialistOptions[0]) }
    var selectedStatus by remember { mutableStateOf(statusOptions[0]) }
    val calendar = Calendar.getInstance()
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    var dateOfBirth by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val indication = null

    var errorMessage by remember { mutableStateOf("") }

    if (showDatePicker) {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(year, month, dayOfMonth)
                dateOfBirth = dateFormat.format(calendar.time)
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            setOnDismissListener { showDatePicker = false }
            show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        TopBottomGradient()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TopAppBar(
                    title = {
                        Text(
                            text = "New Login",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                    modifier = Modifier.background(Color.Transparent)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                TextInput(
                    hint = "First name",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = Icons.Outlined.Person,
                    onValueChanged = { firstName = it },
                    isError = errorMessage.isNotEmpty() && firstName.isEmpty(),
                    errorMessage = if (firstName.isEmpty()) "First name is required" else ""
                )
            }

            item {
                TextInput(
                    hint = "Last name",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = Icons.Outlined.Person,
                    onValueChanged = { lastName = it },
                    isError = errorMessage.isNotEmpty() && lastName.isEmpty(),
                    errorMessage = if (lastName.isEmpty()) "Last name is required" else ""
                )
            }

            item {
                CustomDropdownMenu(
                    options = genderOptions,
                    selectedOption = selectedGender,
                    onOptionSelected = { selectedGender = it },
                    leadingIcon = Icons.Outlined.Transgender
                )
            }

            item {
                CustomDropdownMenu(
                    options = specialistOptions,
                    selectedOption = selectedSpecialist,
                    onOptionSelected = { selectedSpecialist = it },
                    leadingIcon = Icons.Outlined.MedicalServices
                )
            }

            item {
                CustomDropdownMenu(
                    options = statusOptions,
                    selectedOption = selectedStatus,
                    onOptionSelected = { selectedStatus = it },
                    leadingIcon = Icons.Outlined.FavoriteBorder
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        readOnly = true,
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF22C7B8),
                            unfocusedBorderColor = Color.Gray,
                        ),
                        leadingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        Icons.Default.DateRange,
                                        contentDescription = "Select Date",
                                        tint = Color(0xFF22C7B8),
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    VerticalDivider(
                                        color = Color(0xFF22C7B8),
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(4.dp)
                                    )
                                }
                            }
                        },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = indication,
                                        onClick = { showDatePicker = true }
                                    ),
                                text = "Date of Birth",
                                color = Color.Gray
                            )
                        }
                    )
                }
            }


            item {
                TextInput(
                    hint = "Phone number",
                    keyboardType = KeyboardType.Phone,
                    leadingIcon = Icons.Outlined.LocalPhone,
                    onValueChanged = { phoneNumber = it },
                    isError = errorMessage.isNotEmpty() && phoneNumber.isEmpty(),
                    errorMessage = if (phoneNumber.isEmpty()) "Phone number is required" else ""
                )
            }

            item {
                TextInput(
                    hint = "E-mail",
                    keyboardType = KeyboardType.Email,
                    leadingIcon = Icons.Outlined.Email,
                    onValueChanged = { email = it },
                    isError = errorMessage.isNotEmpty() && email.isEmpty(),
                    errorMessage = if (email.isEmpty()) "E-mail is required" else ""
                )
            }

            item {
                TextInput(
                    hint = "Address",
                    keyboardType = KeyboardType.Text,
                    leadingIcon = Icons.Outlined.LocationOn,
                    onValueChanged = { address = it },
                    isError = errorMessage.isNotEmpty() && address.isEmpty(),
                    errorMessage = if (address.isEmpty()) "Address is required" else ""
                )
            }

            item {
                TextInput(
                    hint = "Password",
                    keyboardType = KeyboardType.Password,
                    leadingIcon = Icons.Outlined.Lock,
                    trailingIcon = {
                        val image =
                            if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                        androidx.compose.material3.IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(
                                imageVector = image,
                                contentDescription = null,
                                tint = Color(0xFF22C7B8)
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChanged = { password = it },
                    isError = errorMessage.isNotEmpty() && password.isEmpty(),
                    errorMessage = if (password.isEmpty()) "Password is required" else ""
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        errorMessage = when {
                            firstName.isEmpty() -> "First name is required"
                            lastName.isEmpty() -> "Last name is required"
                            dateOfBirth.isEmpty() -> "Date of birth is required"
                            phoneNumber.isEmpty() -> "Phone number is required"
                            email.isEmpty() -> "E-mail is required"
                            address.isEmpty() -> "Address is required"
                            password.isEmpty() -> "Password is required"
                            selectedGender == "Gender" -> "Gender is required"
                            selectedSpecialist == "Specialist" -> "Specialist is required"
                            selectedStatus == "Status" -> "Status is required"
                            else -> ""
                        }

                        if (errorMessage.isEmpty()) {
                            val registerData = RegisterData(
                                email = email,
                                password = password,
                                firstName = firstName,
                                lastName = lastName,
                                gender = selectedGender,
                                specialist = selectedSpecialist,
                                birthday = dateOfBirth,
                                status = selectedStatus,
                                address = address,
                                mobile = phoneNumber,
                                type = selectedSpecialist
                            )
                            onClickCreate(registerData)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C7B8)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                ) {
                    Text("Create user")
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddEmployeeScreen() {
    AddEmployeeContent {

    }
}