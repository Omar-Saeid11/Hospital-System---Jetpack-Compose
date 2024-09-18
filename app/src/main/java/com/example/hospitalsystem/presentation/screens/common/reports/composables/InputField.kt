package com.example.hospitalsystem.presentation.screens.common.reports.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun InputField(
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    var input by remember { mutableStateOf("") }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = input,
                onValueChange = {
                    input = it
                    onInputChange(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (input.isEmpty()) {
                        Text("Type your Reply", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_upload),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        Button(
            onClick = onSendClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF22C7B8))
        ) {
            Text("Send", color = Color.White)
        }
    }
}