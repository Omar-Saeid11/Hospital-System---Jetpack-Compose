package com.example.hospitalsystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hospitalsystem.presentation.screens.SplashScreen
import com.example.hospitalsystem.presentation.screens.analysis.AnalysisHomeScreen
import com.example.hospitalsystem.presentation.screens.common.reports.CreateReportScreen
import com.example.hospitalsystem.presentation.screens.common.reports.ReportDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.reports.ReportsScreen
import com.example.hospitalsystem.presentation.screens.common.cases.CaseDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.cases.CasesScreen
import com.example.hospitalsystem.presentation.screens.common.cases.SelectionScreen
import com.example.hospitalsystem.presentation.screens.common.cases.requests.MedicalMeasurementScreen
import com.example.hospitalsystem.presentation.screens.common.tasks.TaskDetailsScreen
import com.example.hospitalsystem.presentation.screens.common.tasks.TasksScreen
import com.example.hospitalsystem.presentation.screens.doctor.DoctorCallsScreen
import com.example.hospitalsystem.presentation.screens.doctor.DoctorHomeScreen
import com.example.hospitalsystem.presentation.screens.hr.employee.AddEmployeeScreen
import com.example.hospitalsystem.presentation.screens.hr.HrHomeScreen
import com.example.hospitalsystem.presentation.screens.hr.employee.EmployeeScreen
import com.example.hospitalsystem.presentation.screens.login.LoginScreen
import com.example.hospitalsystem.presentation.screens.manager.ManagerHomeScreen
import com.example.hospitalsystem.presentation.screens.nurse.AddMeasurementScreen
import com.example.hospitalsystem.presentation.screens.nurse.CaseDetailsNurseScreen
import com.example.hospitalsystem.presentation.screens.nurse.NurseHomeScreen
import com.example.hospitalsystem.presentation.screens.profile.ProfileScreen
import com.example.hospitalsystem.presentation.screens.receptionist.ReceptionistScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.CallDetailsScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.CallsScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.CreateCallScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.DoctorSelectionScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.RequestCanceledScreen

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.ReceptionistScreen.route) { ReceptionistScreen(navController) }
        composable(Screen.CallsScreen.route) { CallsScreen(navController) }
        composable(Screen.CreateCallScreen.route) {
            CreateCallScreen(navController = navController)
        }
        composable(Screen.RequestCanceledScreen.route) {
            RequestCanceledScreen(navController = navController)
        }
        composable(
            "${Screen.ProfileScreen.route}/{employeeId}",
            arguments = listOf(navArgument("employeeId") { type = NavType.IntType })
        ) {
            val employeeId = it.arguments?.getInt("employeeId") ?: 0
            ProfileScreen(navController = navController, employeeId = employeeId)
        }
        composable(Screen.HrHomeScreen.route) { HrHomeScreen(navController) }
        composable(Screen.EmployeeScreen.route) { EmployeeScreen(navController) }
        composable(Screen.AddEmployeeScreen.route) {
            AddEmployeeScreen(navController = navController)
        }
        composable(Screen.DoctorSelectionScreen.route) {
            DoctorSelectionScreen(navController = navController)
        }
        composable(
            "${Screen.CallDetailsScreen.route}/{callId}",
            arguments = listOf(navArgument("callId") { type = NavType.IntType })
        ) {
            val callId = it.arguments?.getInt("callId") ?: 0
            CallDetailsScreen(navController = navController, callId = callId)
        }
        composable(Screen.ReportsScreen.route) { ReportsScreen(navController) }
        composable(Screen.CreateReportsScreen.route) { CreateReportScreen(navController) }
        composable(
            "${Screen.ReportDetailsScreen.route}/{reportId}",
            arguments = listOf(navArgument("reportId") { type = NavType.IntType })
        ) {
            val reportId = it.arguments?.getInt("reportId") ?: 0
            ReportDetailsScreen(navController = navController, reportId = reportId)
        }
        composable(Screen.DoctorHomeScreen.route) { DoctorHomeScreen(navController) }
        composable(Screen.DoctorCallsScreen.route) { DoctorCallsScreen(navController) }
        composable(Screen.CasesScreen.route) { CasesScreen(navController = navController) }
        composable(
            "${Screen.ShowCaseScreen.route}/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            CaseDetailsScreen(navController, caseId = caseId)
        }
        composable(
            "${Screen.SelectionScreen.route}/{caseId}/{type}",
            arguments = listOf(
                navArgument("caseId") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            val type = backStackEntry.arguments?.getString("type") ?: ""
            SelectionScreen(navController = navController, caseId = caseId, type = type)
        }

        composable(
            "${Screen.MedicalMeasurementScreen.route}/{caseId}/{nurseId}",
            arguments = listOf(
                navArgument("caseId") { type = NavType.IntType },
                navArgument("nurseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            val nurseId = backStackEntry.arguments?.getInt("nurseId") ?: 0
            MedicalMeasurementScreen(navController, caseId, nurseId)
        }
        composable(
            "${Screen.CaseDetailsNurseScreen.route}/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            CaseDetailsNurseScreen(navController, caseId = caseId)
        }
        composable(Screen.NurseHomeScreen.route) { NurseHomeScreen(navController = navController) }
        composable(Screen.TasksScreen.route) { TasksScreen(navController = navController) }
        composable(
            "${Screen.TaskDetailsScreen.route}/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailsScreen(navController, taskId = taskId)
        }
        composable(Screen.AnalysisScreen.route) { AnalysisHomeScreen(navController = navController) }
        composable(Screen.ManagerScreen.route) { ManagerHomeScreen(navController = navController) }
        composable(
            "${Screen.AddMeasurementScreen.route}/{caseId}",
            arguments = listOf(navArgument("caseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val caseId = backStackEntry.arguments?.getInt("caseId") ?: 0
            AddMeasurementScreen(navController = navController, caseId = caseId)
        }
        composable("createReport") {
            com.example.hospitalsystem.presentation.screens.manager.CreateReportScreen(
                onBackClick = { navController.popBackStack() },
                onUploadClick = { },
                onCreateReportClick = {  },
                navController = navController
            )
        }
    }
}
