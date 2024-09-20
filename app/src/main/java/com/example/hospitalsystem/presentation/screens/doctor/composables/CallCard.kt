package com.example.hospitalsystem.presentation.screens.doctor.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.theme.Primary

@Composable
fun CallCard(name: String, date: String, onAccept: () -> Unit, onCancel: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = name, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            Primary,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = onAccept,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5FDC89)),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .padding(2.dp)
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Accept", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA500)),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .padding(2.dp)
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Busy", color = Color.White)
                }
            }
        }
    }
}

