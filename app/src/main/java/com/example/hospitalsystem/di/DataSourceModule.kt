package com.example.hospitalsystem.di

import com.example.hospitalsystem.data.api.attendance.AttendanceApiService
import com.example.hospitalsystem.data.api.attendance.ImplAttendanceDataSource
import com.example.hospitalsystem.data.api.attendance.IntAttendanceDataSource
import com.example.hospitalsystem.data.api.calls.CallsApiService
import com.example.hospitalsystem.data.api.calls.ImplCallsDataSource
import com.example.hospitalsystem.data.api.calls.IntCallsDataSource
import com.example.hospitalsystem.data.api.cases.CasesApiService
import com.example.hospitalsystem.data.api.cases.ImplCasesDataSource
import com.example.hospitalsystem.data.api.cases.IntCasesDataSource
import com.example.hospitalsystem.data.api.hr.HrApiService
import com.example.hospitalsystem.data.api.hr.ImplHrDataSource
import com.example.hospitalsystem.data.api.hr.IntHrDataSource
import com.example.hospitalsystem.data.api.login.ImplLoginDataSource
import com.example.hospitalsystem.data.api.login.IntLoginDataSource
import com.example.hospitalsystem.data.api.login.LoginApiService
import com.example.hospitalsystem.data.api.profile.ImplProfileDataSource
import com.example.hospitalsystem.data.api.profile.IntProfileDataSource
import com.example.hospitalsystem.data.api.profile.ProfileApiService
import com.example.hospitalsystem.data.api.reports.ImplReportDataSource
import com.example.hospitalsystem.data.api.reports.IntReportDataSource
import com.example.hospitalsystem.data.api.reports.ReportApiService
import com.example.hospitalsystem.data.api.tasks.ImplTasksDataSource
import com.example.hospitalsystem.data.api.tasks.IntTasksDataSource
import com.example.hospitalsystem.data.api.tasks.TasksApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    @Singleton
    fun provideIntHrDataSource(hrApiService: HrApiService): IntHrDataSource {
        return ImplHrDataSource(hrApiService)
    }

    @Provides
    @Singleton
    fun provideIntLoginDataSource(loginApiService: LoginApiService): IntLoginDataSource {
        return ImplLoginDataSource(loginApiService)
    }

    @Provides
    @Singleton
    fun provideIntProfileDataSource(profileApiService: ProfileApiService): IntProfileDataSource {
        return ImplProfileDataSource(profileApiService)
    }

    @Provides
    @Singleton
    fun provideIntCallsDataSource(callsApiService: CallsApiService): IntCallsDataSource {
        return ImplCallsDataSource(callsApiService)
    }

    @Provides
    @Singleton
    fun provideIntReportDataSource(reportApiService: ReportApiService): IntReportDataSource {
        return ImplReportDataSource(reportApiService)
    }

    @Provides
    @Singleton
    fun provideIntCasesDataSource(casesApiService: CasesApiService): IntCasesDataSource {
        return ImplCasesDataSource(casesApiService)
    }

    @Provides
    @Singleton
    fun provideIntTasksDataSource(tasksApiService: TasksApiService): IntTasksDataSource {
        return ImplTasksDataSource(tasksApiService)
    }

    @Provides
    @Singleton
    fun provideIntAttendanceDataSource(attendanceApiService: AttendanceApiService): IntAttendanceDataSource {
        return ImplAttendanceDataSource(attendanceApiService)
    }
}
