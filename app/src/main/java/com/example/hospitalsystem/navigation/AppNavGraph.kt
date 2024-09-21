package com.example.hospitalsystem.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hospitalsystem.presentation.screens.SplashScreen
import com.example.hospitalsystem.presentation.screens.analysis.AnalysisHomeScreen
import com.example.hospitalsystem.presentation.screens.attendance.AttendanceAndLeavingScreen
import com.example.hospitalsystem.presentation.screens.attendance.FingerprintScreen
import com.example.hospitalsystem.presentation.screens.attendance.RegisterSuccessScreen
import com.example.hospitalsystem.presentation.screens.common.cases.CaseDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.cases.CasesScreen
import com.example.hospitalsystem.presentation.screens.common.cases.SelectionScreen
import com.example.hospitalsystem.presentation.screens.common.cases.requests.MedicalMeasurementScreen
import com.example.hospitalsystem.presentation.screens.common.reports.CreateReportScreen
import com.example.hospitalsystem.presentation.screens.common.reports.ReportDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.reports.ReportsScreen
import com.example.hospitalsystem.presentation.screens.common.tasks.TaskDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.tasks.TasksScreen
import com.example.hospitalsystem.presentation.screens.doctor.DoctorCallsScreen
import com.example.hospitalsystem.presentation.screens.doctor.DoctorHomeScreen
import com.example.hospitalsystem.presentation.screens.hr.HrHomeScreen
import com.example.hospitalsystem.presentation.screens.hr.employee.AddEmployeeScreen
import com.example.hospitalsystem.presentation.screens.hr.employee.EmployeeScreen
import com.example.hospitalsystem.presentation.screens.login.LoginScreen
import com.example.hospitalsystem.presentation.screens.manager.ManagerHomeScreen
import com.example.hospitalsystem.presentation.screens.nurse.AddMeasurementScreen
import com.example.hospitalsystem.presentation.screens.nurse.CaseDetailsNurseScreen
import com.example.hospitalsystem.presentation.screens.profile.ProfileScreen
import com.example.hospitalsystem.presentation.screens.receptionist.ReceptionistScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.CallDetailsScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.CallsScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.CreateCallScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.DoctorSelectionScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.RequestCanceledScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {
        // General Screens
        composable(Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.AttendanceAndLeavingScreen.route) {
            AttendanceAndLeavingScreen(
                navController
            )
        }
        composable(Screen.RegisterSuccessScreen.route) { RegisterSuccessScreen(navController) }

        // Receptionist Screens
        composable(Screen.ReceptionistScreen.route) { ReceptionistScreen(navController) }
        composable(Screen.CallsScreen.route) { CallsScreen(navController) }
        composable(Screen.CreateCallScreen.route) { CreateCallScreen(navController) }
        composable(Screen.RequestCanceledScreen.route) { RequestCanceledScreen(navController) }
        composable(
            Screen.CallDetailsScreen.route + "/{callId}",
            arguments = listOf(navArgument("callId") { type = NavType.IntType })
        ) {
            val callId = it.arguments?.getInt("callId") ?: 0
            CallDetailsScreen(navController, callId)
        }

        // HR Screens
        composable(Screen.HrHomeScreen.route) { HrHomeScreen(navController) }
        composable(Screen.EmployeeScreen.route) { EmployeeScreen(navController) }
        composable(Screen.AddEmployeeScreen.route) { AddEmployeeScreen(navController) }
        composable(
            Screen.ProfileScreen.route + "/{employeeId}",
            arguments = listOf(navArgument("employeeId") { type = NavType.IntType })
        ) {
            val employeeId = it.arguments?.getInt("employeeId") ?: 0
            ProfileScreen(navController, employeeId = employeeId)
        }

        // Doctor Screens
        composable(Screen.DoctorHomeScreen.route) { DoctorHomeScreen(navController) }
        composable(Screen.DoctorCallsScreen.route) { DoctorCallsScreen(navController) }
        composable(Screen.DoctorSelectionScreen.route) { DoctorSelectionScreen(navController) }

        // Case Screens
        composable(Screen.CasesScreen.route) { CasesScreen(navController) }
        composable(
            Screen.ShowCaseScreen.route + "/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) {
            val caseId = it.arguments?.getInt("caseId") ?: 0
            CaseDetailsScreen(navController, caseId)
        }
        composable(
            Screen.MedicalMeasurementScreen.route + "/{caseId}/{nurseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType },
                navArgument("nurseId") { type = NavType.IntType })
        ) {
            val caseId = it.arguments?.getInt("caseId") ?: 0
            val nurseId = it.arguments?.getInt("nurseId") ?: 0
            MedicalMeasurementScreen(navController, caseId, nurseId)
        }
        composable(
            Screen.CaseDetailsNurseScreen.route + "/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) {
            val caseId = it.arguments?.getInt("caseId") ?: 0
            CaseDetailsNurseScreen(navController, caseId)
        }

        // Report Screens
        composable(Screen.ReportsScreen.route) { ReportsScreen(navController) }
        composable(Screen.CreateReportsScreen.route) { CreateReportScreen(navController) }
        composable(
            Screen.ReportDetailsScreen.route + "/{reportId}",
            arguments = listOf(navArgument("reportId") { type = NavType.IntType })
        ) {
            val reportId = it.arguments?.getInt("reportId") ?: 0
            ReportDetailsScreen(navController, reportId)
        }

        // Tasks Screens
        composable(Screen.TasksScreen.route) { TasksScreen(navController) }
        composable(
            Screen.TaskDetailsScreen.route + "/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) {
            val taskId = it.arguments?.getInt("taskId") ?: 0
            TaskDetailsScreen(navController, taskId)
        }

        // Miscellaneous
        composable(Screen.AnalysisScreen.route) { AnalysisHomeScreen(navController) }
        composable(Screen.ManagerScreen.route) { ManagerHomeScreen(navController) }

        // Screen with multiple arguments
        composable(
            Screen.SelectionScreen.route + "/{caseId}/{type}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            val type = backStackEntry.arguments?.getString("type") ?: ""
            SelectionScreen(navController, caseId = caseId, type = type)
        }

        // FingerprintScreen with parameter
        composable(
            route = "${Screen.FingerprintScreen.route}/{type}",
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            FingerprintScreen(navController, type) // Ensure this is your composable function
        }

        // Add Measurement
        composable(
            Screen.AddMeasurementScreen.route + "/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) {
            val caseId = it.arguments?.getInt("caseId") ?: 0
            AddMeasurementScreen(navController, caseId = caseId)
        }

        // Example: Create report screen without the Screen object
        composable("createReport") {
            com.example.hospitalsystem.presentation.screens.manager.CreateReportScreen(
                onBackClick = { navController.popBackStack() },
                onUploadClick = { },
                onCreateReportClick = { },
                navController = navController
            )
        }
    }
}
