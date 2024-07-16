package com.example.hospitalsystem.application.di

import com.example.hospitalsystem.data.api.calls.CallsApiService
import com.example.hospitalsystem.data.api.calls.ImplCallsDataSource
import com.example.hospitalsystem.data.api.calls.IntCallsDataSource
import com.example.hospitalsystem.data.api.hr.HrApiService
import com.example.hospitalsystem.data.api.hr.ImplHrDataSource
import com.example.hospitalsystem.data.api.hr.IntHrDataSource
import com.example.hospitalsystem.data.api.login.ImplLoginDataSource
import com.example.hospitalsystem.data.api.login.IntLoginDataSource
import com.example.hospitalsystem.data.api.login.LoginApiService
import com.example.hospitalsystem.data.api.profile.ImplProfileDataSource
import com.example.hospitalsystem.data.api.profile.IntProfileDataSource
import com.example.hospitalsystem.data.api.profile.ProfileApiService
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
}
