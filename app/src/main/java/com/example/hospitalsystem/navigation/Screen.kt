package com.example.hospitalsystem.navigation

sealed class Screen(val route: String) {
    // General Screens
    data object SplashScreen : Screen("splashScreen")
    data object LoginScreen : Screen("loginScreen")
    data object ProfileScreen : Screen("profileScreen")
    data object RegisterSuccessScreen : Screen("registerSuccessScreen")

    // Receptionist-related Screens
    data object ReceptionistScreen : Screen("receptionistScreen")
    data object CallsScreen : Screen("callsScreen")
    data object CreateCallScreen : Screen("createCallScreen")
    data object CallDetailsScreen : Screen("callDetailsScreen")
    data object RequestCanceledScreen : Screen("requestCanceledScreen")

    // HR-related Screens
    data object HrHomeScreen : Screen("hrHomeScreen")
    data object EmployeeScreen : Screen("employeeScreen")
    data object AddEmployeeScreen : Screen("addEmployeeScreen")
    data object AttendanceAndLeavingScreen : Screen("attendanceAndLeavingScreen")

    // Doctor-related Screens
    data object DoctorHomeScreen : Screen("doctorHomeScreen")
    data object DoctorCallsScreen : Screen("doctorCallsScreen")
    data object DoctorSelectionScreen : Screen("doctorSelectionScreen")

    // Case-related Screens
    data object CasesScreen : Screen("casesScreen")
    data object ShowCaseScreen : Screen("showCaseScreen")
    data object MedicalMeasurementScreen : Screen("medicalMeasurementScreen")
    data object AddMeasurementScreen : Screen("addMeasurementScreen")
    data object CaseDetailsNurseScreen : Screen("caseDetailsNurseScreen")

    // Reports-related Screens
    data object ReportsScreen : Screen("reportsScreen")
    data object CreateReportsScreen : Screen("createReportsScreen")
    data object ReportDetailsScreen : Screen("reportDetailsScreen")

    // Task-related Screens
    data object TasksScreen : Screen("tasksScreen")
    data object TaskDetailsScreen : Screen("taskDetailsScreen")

    // Other Screens
    data object AnalysisScreen : Screen("analysisScreen")
    data object ManagerScreen : Screen("managerScreen")
    data object NurseHomeScreen : Screen("nurseHomeScreen")
    data object SelectionScreen : Screen("selectionScreen")

    // Parameterized Screen for Fingerprint
    data object FingerprintScreen : Screen("fingerprintScreen")
}
