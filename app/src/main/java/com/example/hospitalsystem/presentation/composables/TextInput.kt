package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    hint: String,
    keyboardType: KeyboardType,
    leadingIcon: ImageVector?,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
    isError: Boolean = false,
    readOnly:Boolean=false,
    errorMessage: String // Now a required parameter
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChanged(it)
            },
            shape = RoundedCornerShape(16.dp),
            label = { Text(hint) },
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color(0xFF22C7B8)
                    )
                }
            },
            readOnly = readOnly,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF22C7B8),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF22C7B8)
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputCalls(
    modifier: Modifier = Modifier,
    hint: String,
    lines:Boolean,
    keyboardType: KeyboardType,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String // Now a required parameter
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChanged(it)
            },
            shape = RoundedCornerShape(16.dp),
            label = { Text(hint) },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF22C7B8),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF22C7B8)
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            singleLine = lines,
            modifier = modifier.fillMaxWidth()
        )
        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}