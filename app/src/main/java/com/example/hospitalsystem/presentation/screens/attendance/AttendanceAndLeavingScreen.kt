package com.example.hospitalsystem.presentation.screens.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.composables.TopSection
import com.example.hospitalsystem.presentation.screens.attendance.composables.AttendanceCard
import com.example.hospitalsystem.presentation.screens.attendance.composables.NoteSection

@Composable
fun AttendanceAndLeavingScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopSection(onClickProfileImg = {
            val userId = UserPreferences.getUserId()
            navController.navigate("${Screen.ProfileScreen.route}/$userId")
        })
        Spacer(modifier = Modifier.height(16.dp))
        NoteSection(note = "Details note : Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
        Spacer(modifier = Modifier.height(16.dp))

        AttendanceCard(
            title = "Attendance",
            time = "09:00 am",
            isRegistered = true,
            onClick = {
                navController.navigate("${Screen.FingerprintScreen.route}/attendance")
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        AttendanceCard(
            title = "Leaving",
            time = "04:00 pm",
            isRegistered = false,
            onClick = {
                navController.navigate("${Screen.FingerprintScreen.route}/leaving")
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevAttendanceAndLeavingScreen() {
    AttendanceAndLeavingScreen(navController = rememberNavController())
}