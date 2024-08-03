package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType

@Composable
fun EmployeeItem(employee: PresentationUserType, onClickItem: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .clickable { onClickItem() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.ic_user),
                contentDescription = employee.firstName,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Icon(
                Icons.Default.Circle,
                contentDescription = "Available",
                tint = Color.Green,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(12.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(employee.firstName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(employee.type, color = Color.Gray, fontSize = 14.sp)
        }
    }
}