package com.example.hospitalsystem.di

import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import com.example.hospitalsystem.domain.repository.casesRepo.IntCasesRepository
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
import com.example.hospitalsystem.domain.repository.reportRepo.IntReportRepository
import com.example.hospitalsystem.domain.repository.tasksRepo.IntTasksRepository
import com.example.hospitalsystem.domain.usecase.callsUseCase.CallsUseCase
import com.example.hospitalsystem.domain.usecase.casesUseCase.CasesUseCase
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrGetUserTypeUseCase
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrRegisterUseCase
import com.example.hospitalsystem.domain.usecase.loginUseCase.LoginUseCase
import com.example.hospitalsystem.domain.usecase.profileUseCase.ProfileUseCase
import com.example.hospitalsystem.domain.usecase.reportUseCase.ReportUseCase
import com.example.hospitalsystem.domain.usecase.tasksUseCase.TasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideHrRegisterUseCase(intHrRepository: IntHrRepository): HrRegisterUseCase {
        return HrRegisterUseCase(intHrRepository)
    }

    @Provides
    fun provideHrGetUserTypeUseCase(intHrRepository: IntHrRepository): HrGetUserTypeUseCase {
        return HrGetUserTypeUseCase(intHrRepository)
    }

    @Provides
    fun provideLoginUseCase(intLoginRepository: IntLoginRepository): LoginUseCase {
        return LoginUseCase(intLoginRepository)
    }

    @Provides
    fun provideProfileUseCase(intProfileRepository: IntProfileRepository): ProfileUseCase {
        return ProfileUseCase(intProfileRepository)
    }

    @Provides
    fun provideCallsUseCase(intCallsRepository: IntCallsRepository): CallsUseCase {
        return CallsUseCase(intCallsRepository)
    }

    @Provides
    fun provideReportUseCase(intReportRepository: IntReportRepository): ReportUseCase {
        return ReportUseCase(intReportRepository)
    }

    @Provides
    fun provideCasesUseCase(intCasesRepository: IntCasesRepository): CasesUseCase {
        return CasesUseCase(intCasesRepository)
    }

    @Provides
    fun provideTasksUseCase(intTasksRepository: IntTasksRepository): TasksUseCase {
        return TasksUseCase(intTasksRepository)
    }
}
