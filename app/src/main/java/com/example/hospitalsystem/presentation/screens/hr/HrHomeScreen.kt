package com.example.hospitalsystem.presentation.screens.hr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hospitalsystem.R
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.presentation.screens.receptionist.GridSection
import com.example.hospitalsystem.presentation.screens.receptionist.TopSection

@Composable
fun HrHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopSection(onClickProfileImg = { navController.navigate(Screen.ProfileScreen.route) })
        Spacer(modifier = Modifier.height(16.dp))
        GridSection(
            colorCard1 = 0xFFDC915F,
            colorCard2 = 0xFF915FDC,
            colorCard3 = 0xFF5FDC89,
            colorCard4 = 0xFF26C6DA,
            titleCard1 = "Employee",
            titleCard2 = "Reports",
            titleCard3 = "Tasks",
            titleCard4 = "Attendance\n -\n Leaving",
            iconCard1 = R.drawable.employee,
            iconCard2 = R.drawable.ic_reports,
            iconCard3 = R.drawable.ic_tasks,
            iconCard4 = R.drawable.ic_attendance,
            onClickCard1 = { navController.navigate(Screen.EmployeeScreen.route) },
            onClickCard2 = {},
            onClickCard3 = {},
            onClickCard4 = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrevHrHomeScreen() {
    HrHomeScreen(navController = NavController(LocalContext.current))
}