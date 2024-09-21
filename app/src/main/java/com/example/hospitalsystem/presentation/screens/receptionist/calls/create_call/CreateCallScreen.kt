package com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.TextInputCalls
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCallScreen(
    navController: NavController,
    callsViewModel: CallsViewModel = hiltViewModel()
) {
    var patientName by remember { mutableStateOf("") }
    var patientError by remember { mutableStateOf(false) }
    var age by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    var phoneNumberError by remember { mutableStateOf(false) }
    var selectedDoctor by remember { mutableStateOf<PresentationUserType?>(null) }
    var doctorError by remember { mutableStateOf(false) }
    var caseDescription by remember { mutableStateOf("") }
    var descriptionError by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val indication = null

    val selectedDoctorState = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<PresentationUserType>("selectedDoctor")

    selectedDoctorState?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { doctor ->
        selectedDoctor = doctor
    }
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<String>("patientName")
        ?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { value ->
            if (value != null) patientName = value
        }
    savedStateHandle?.getLiveData<String>("age")?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { value ->
        if (value != null) age = value
    }
    savedStateHandle?.getLiveData<String>("phoneNumber")
        ?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { value ->
            if (value != null) phoneNumber = value
        }
    savedStateHandle?.getLiveData<String>("caseDescription")
        ?.observe(androidx.lifecycle.compose.LocalLifecycleOwner.current) { value ->
            if (value != null) caseDescription = value
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
            title = {
                Text(
                    text = "Create Call",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInputCalls(
            hint = "Patient Name",
            keyboardType = KeyboardType.Text,
            onValueChanged = { newValue ->
                patientName = newValue
                savedStateHandle?.set("patientName", newValue)
            },
            isError = patientError,
            errorMessage = stringResource(R.string.required),
            lines = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextInputCalls(
            hint = "Age",
            keyboardType = KeyboardType.Number,
            onValueChanged = { newValue ->
                age = newValue
                savedStateHandle?.set("age", newValue)
            },
            isError = ageError,
            errorMessage = stringResource(R.string.required),
            lines = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextInputCalls(
            hint = "Phone Number",
            keyboardType = KeyboardType.Number,
            onValueChanged = { newValue ->
                phoneNumber = newValue
                savedStateHandle?.set("phoneNumber", newValue)
            },
            isError = phoneNumberError,
            errorMessage = stringResource(R.string.required),
            lines = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = selectedDoctor?.firstName ?: "",
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true,
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Gray,
            ),
            placeholder = {
                Text(
                    text = "Select Doctor",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = indication,
                            onClick = { navController.navigate(Screen.DoctorSelectionScreen.route) }
                        ),
                )
            },
            isError = doctorError
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextInputCalls(
            hint = "Case Description",
            keyboardType = KeyboardType.Text,
            onValueChanged = { newValue ->
                caseDescription = newValue
                savedStateHandle?.set("caseDescription", newValue)
            },
            isError = descriptionError,
            errorMessage = stringResource(R.string.required),
            lines = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                patientError = patientName.isEmpty()
                ageError = age.isEmpty()
                phoneNumberError = phoneNumber.isEmpty()
                doctorError = selectedDoctor == null
                descriptionError = caseDescription.isEmpty()

                if (!patientError && !ageError && !phoneNumberError && !doctorError && !descriptionError) {
                    val callData = selectedDoctor?.let {
                        CallData(
                            patientName = patientName,
                            age = age.toInt(),
                            doctorId = it.id,
                            phone = phoneNumber,
                            description = caseDescription
                        )
                    }
                    if (callData != null) {
                        callsViewModel.createCall(callData)
                    }
                    navController.navigate(Screen.RequestCanceledScreen.route){
                        popUpTo(
                            navController.graph.startDestinationRoute ?: "startDestination"
                        ) {
                            inclusive = false
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Primary),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(text = "Send Call")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CreateCallScreenPreview() {
    CreateCallScreen(navController = NavController(LocalContext.current))
}
