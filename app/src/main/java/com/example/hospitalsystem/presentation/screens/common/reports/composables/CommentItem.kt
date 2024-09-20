package com.example.hospitalsystem.presentation.screens.common.reports.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalsystem.R
import com.example.hospitalsystem.theme.Primary

@Composable
fun CommentItem(
    name: String,
    role: String,
    date: String,
    comment: String,
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(name, fontWeight = FontWeight.Bold)
                Text(role, color = Primary, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(date, color = Color.Gray, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(comment)
        Spacer(modifier = Modifier.height(8.dp))
    }
}