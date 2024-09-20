package com.example.hospitalsystem.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalsystem.R
import com.example.hospitalsystem.presentation.composables.TextInput
import com.example.hospitalsystem.presentation.composables.TopBottomGradient
import com.example.hospitalsystem.theme.Primary

@Composable
fun LoginContent(
    signIn: (email: String, password: String, token: String) -> Unit,
    loading: Boolean
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBottomGradient()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_splash_screen),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome back!",
                    color = Primary,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "To continue, login now",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextInput(
                hint = "Email",
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Outlined.Email,
                onValueChanged = { newValue ->
                    email = newValue
                },
                isError = emailError,
                errorMessage = stringResource(R.string.required)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextInput(
                hint = "Password",
                keyboardType = KeyboardType.Password,
                leadingIcon = Icons.Outlined.Lock,
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChanged = { newValue ->
                    password = newValue
                },
                isError = passwordError,
                errorMessage = stringResource(R.string.required)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forget Password",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    emailError = email.isEmpty()
                    passwordError = password.isEmpty()
                    if (!emailError && !passwordError) {
                        signIn(email, password, "")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Primary),
                contentPadding = PaddingValues(12.dp)
            ) {
                if (loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.sign_in),
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}