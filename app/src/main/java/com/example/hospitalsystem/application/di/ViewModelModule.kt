package com.example.hospitalsystem.application.di

import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
import com.example.hospitalsystem.domain.usecase.callsUseCase.CallsUseCase
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrGetUserTypeUseCase
import com.example.hospitalsystem.domain.usecase.hrUserCase.HrRegisterUseCase
import com.example.hospitalsystem.domain.usecase.loginUseCase.LoginUseCase
import com.example.hospitalsystem.domain.usecase.profileUseCase.ProfileUseCase
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
}
