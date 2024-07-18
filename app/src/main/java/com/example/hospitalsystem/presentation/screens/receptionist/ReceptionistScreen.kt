package com.example.hospitalsystem.presentation.screens.receptionist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.core.UserPreferences

@Composable
fun ReceptionistScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopSection(onClickProfileImg = { navController.navigate(Screen.ProfileScreen.route) })
        Spacer(modifier = Modifier.height(16.dp))
        GridSection(
            colorCard1 = 0xFF42A5F5,
            colorCard2 = 0xFF915FDC,
            colorCard3 = 0xFF5FDC89,
            colorCard4 = 0xFF26C6DA,
            titleCard1 = "Calls",
            titleCard2 = "Reports",
            titleCard3 = "Tasks",
            titleCard4 = "Attendance\n -\n Leaving",
            iconCard1 = R.drawable.ic_add_call,
            iconCard2 = R.drawable.ic_reports,
            iconCard3 = R.drawable.ic_tasks,
            iconCard4 = R.drawable.ic_attendance,
            onClickCard1 = { navController.navigate(Screen.CallsScreen.route) },
            onClickCard2 = { navController.navigate(Screen.ReportsScreen.route) },
            onClickCard3 = {},
            onClickCard4 = {}
        )
    }
}

@Composable
fun TopSection(onClickProfileImg: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val indication = null

        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = indication,
                    onClick = { onClickProfileImg() }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Transparent, CircleShape)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(UserPreferences.getUserName(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    "Specialist, ${UserPreferences.getUserType()}",
                    color = Color(0xFF66BB6A),
                    fontSize = 14.sp
                )
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Notifications",
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
        )
    }
}

@Composable
fun GridSection(
    colorCard1: Long,
    colorCard2: Long,
    colorCard3: Long,
    colorCard4: Long,
    titleCard1: String,
    titleCard2: String,
    titleCard3: String,
    titleCard4: String,
    iconCard1: Int,
    iconCard2: Int,
    iconCard3: Int,
    iconCard4: Int,
    onClickCard1: () -> Unit,
    onClickCard2: () -> Unit,
    onClickCard3: () -> Unit,
    onClickCard4: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HomeCard(
                title = titleCard1,
                color = Color(colorCard1),
                modifier = Modifier
                    .height(220.dp)
                    .width(165.dp),
                iconDrawable = iconCard1
            ) { onClickCard1() }
            HomeCard(
                title = titleCard2,
                color = Color(colorCard2),
                modifier = Modifier
                    .height(180.dp)
                    .width(165.dp),
                iconDrawable = iconCard2
            ) { onClickCard2() }
        }
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            HomeCard(
                title = titleCard3,
                color = Color(colorCard3),
                modifier = Modifier
                    .height(180.dp)
                    .width(165.dp),
                iconDrawable = iconCard3
            ) { onClickCard3() }
            HomeCard(
                title = titleCard4,
                color = Color(colorCard4),
                modifier = Modifier
                    .height(220.dp)
                    .width(165.dp),
                iconDrawable = iconCard4
            ) { onClickCard4() }
        }
    }
}

@Composable
fun HomeCard(
    title: String,
    color: Color,
    modifier: Modifier = Modifier,
    iconDrawable: Int,
    onClick: () -> Unit
) {
    Card(
        backgroundColor = color,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconDrawable), // Replace with your icon
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                textAlign = TextAlign.Center,
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
