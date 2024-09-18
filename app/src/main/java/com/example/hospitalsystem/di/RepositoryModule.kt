package com.example.hospitalsystem.di

import com.example.hospitalsystem.data.api.calls.IntCallsDataSource
import com.example.hospitalsystem.data.api.cases.IntCasesDataSource
import com.example.hospitalsystem.data.api.hr.IntHrDataSource
import com.example.hospitalsystem.data.api.login.IntLoginDataSource
import com.example.hospitalsystem.data.api.profile.IntProfileDataSource
import com.example.hospitalsystem.data.api.reports.IntReportDataSource
import com.example.hospitalsystem.data.api.tasks.ImplTasksDataSource
import com.example.hospitalsystem.data.repositories.callsRepo.ImplCallsRepository
import com.example.hospitalsystem.data.repositories.casesRepo.ImplCasesRepository
import com.example.hospitalsystem.data.repositories.hrRepo.ImpHrRepository
import com.example.hospitalsystem.data.repositories.loginRepo.ImplLoginRepository
import com.example.hospitalsystem.data.repositories.profileRepo.ImplProfileRepository
import com.example.hospitalsystem.data.repositories.reportRepo.ImplReportRepository
import com.example.hospitalsystem.data.repositories.tasksRepo.ImplTasksRepository
import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import com.example.hospitalsystem.domain.repository.casesRepo.IntCasesRepository
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
import com.example.hospitalsystem.domain.repository.reportRepo.IntReportRepository
import com.example.hospitalsystem.domain.repository.tasksRepo.IntTasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideImpHrRepository(intHrDataSource: IntHrDataSource): IntHrRepository {
        return ImpHrRepository(intHrDataSource)
    }

    @Provides
    @Singleton
    fun provideImpLoginRepository(intLoginDataSource: IntLoginDataSource): IntLoginRepository {
        return ImplLoginRepository(intLoginDataSource)
    }

    @Provides
    @Singleton
    fun provideImpProfileRepository(intProfileDataSource: IntProfileDataSource): IntProfileRepository {
        return ImplProfileRepository(intProfileDataSource)
    }

    @Provides
    @Singleton
    fun provideImpCallsRepository(intCallsDataSource: IntCallsDataSource): IntCallsRepository {
        return ImplCallsRepository(intCallsDataSource)
    }

    @Provides
    @Singleton
    fun provideImpReportRepository(intReportDataSource: IntReportDataSource): IntReportRepository {
        return ImplReportRepository(intReportDataSource)
    }

    @Provides
    @Singleton
    fun provideImpCasesRepository(intCasesDataSource: IntCasesDataSource): IntCasesRepository {
        return ImplCasesRepository(intCasesDataSource)
    }

    @Provides
    @Singleton
    fun provideImplTasksRepository(intTasksDataSource: ImplTasksDataSource): IntTasksRepository {
        return ImplTasksRepository(intTasksDataSource)
    }
}
