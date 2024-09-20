package com.example.hospitalsystem.presentation.screens.common.cases.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
fun CaseCard(name: String, date: String, onShowDetailsClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                androidx.compose.material.Icon(
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
                Text(text = date, fontSize = 14.sp)
            }
            Button(
                onClick = onShowDetailsClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Show Details",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}
