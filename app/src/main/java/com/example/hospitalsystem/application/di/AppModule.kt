package com.example.hospitalsystem.application.di


import android.app.Application
import android.content.Context
import com.example.hospitalsystem.core.AuthPref
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.data.api.calls.CallsApiService
import com.example.hospitalsystem.data.api.cases.CasesApiService
import com.example.hospitalsystem.data.api.hr.HrApiService
import com.example.hospitalsystem.data.api.login.LoginApiService
import com.example.hospitalsystem.data.api.profile.ProfileApiService
import com.example.hospitalsystem.data.api.reports.ReportApiService
import com.example.hospitalsystem.presentation.connectivity.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://hospital.elhossiny.net/api/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val url = originalRequest.url.newBuilder().build()
                val request = originalRequest.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer ${UserPreferences.getUserToken()}")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideHrApiService(retrofit: Retrofit): HrApiService {
        return retrofit.create(HrApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiService {
        return retrofit.create(ProfileApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService {
        return retrofit.create(LoginApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCallsApiService(retrofit: Retrofit): CallsApiService {
        return retrofit.create(CallsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideReportApiService(retrofit: Retrofit): ReportApiService {
        return retrofit.create(ReportApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCasesApiService(retrofit: Retrofit): CasesApiService {
        return retrofit.create(CasesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideNetworkObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserver(context)
    }

    @Provides
    @Singleton
    fun provideAuthPref(@ApplicationContext context: Context): AuthPref {
        return AuthPref(context)
    }

}
