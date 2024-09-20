package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.presentation.models.calls.PresentationCallData
import com.example.hospitalsystem.theme.Primary

@Composable
fun CallRow(call: PresentationCallData, onCallClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onCallClick() },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(Color.White)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                )
                {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Contact",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(2.dp)
                            .background(
                                Primary,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(call.patientName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                if (call.status == "accept_doctor") {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Completed",
                        tint = Color.Green,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.ic_delay),
                        contentDescription = "Missed",
                        modifier = Modifier.size(24.dp)
                    )
                }

            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp)
                        .background(
                            Primary,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(call.createdAt, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
