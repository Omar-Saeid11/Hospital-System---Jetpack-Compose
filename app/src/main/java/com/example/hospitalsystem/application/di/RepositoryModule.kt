package com.example.hospitalsystem.application.di

import com.example.hospitalsystem.data.api.calls.IntCallsDataSource
import com.example.hospitalsystem.data.api.hr.IntHrDataSource
import com.example.hospitalsystem.data.api.login.IntLoginDataSource
import com.example.hospitalsystem.data.api.profile.IntProfileDataSource
import com.example.hospitalsystem.data.repositories.callsRepo.ImplCallsRepository
import com.example.hospitalsystem.data.repositories.hrRepo.ImpHrRepository
import com.example.hospitalsystem.data.repositories.loginRepo.ImplLoginRepository
import com.example.hospitalsystem.data.repositories.profileRepo.ImplProfileRepository
import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
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
}