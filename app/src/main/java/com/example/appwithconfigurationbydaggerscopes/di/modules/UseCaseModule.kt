package com.example.appwithconfigurationbydaggerscopes.di.modules

import com.example.appwithconfigurationbydaggerscopes.data.usecase.LoginUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.LogoutUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveActiveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.SetupBaseUrlUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.UpdateActiveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.UpdateMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupBaseUrlUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesSetupBaseUrlUseCase(settings: Settings): SetupBaseUrlUseCase = SetupBaseUrlUseCaseImpl(settings)

    @Provides
    @Singleton
    fun providesUpdateActiveMemoryVariableUseCase(activeMemoryRepository: ActiveMemoryRepository): UpdateActiveMemoryVariableUseCase =
        UpdateActiveMemoryVariableUseCaseImpl(activeMemoryRepository)

    @Provides
    @Singleton
    fun providesUpdateMemoryVariableUseCase(memoryRepository: MemoryRepository): UpdateMemoryVariableUseCase =
        UpdateMemoryVariableUseCaseImpl(memoryRepository)

    @Provides
    @Singleton
    fun providesObserveActiveMemoryVariableUseCase(activeMemoryRepository: ActiveMemoryRepository): ObserveActiveMemoryVariableUseCase =
        ObserveActiveMemoryVariableUseCaseImpl(activeMemoryRepository)

    @Provides
    @Singleton
    fun providesObserveMemoryVariableUseCase(memoryRepository: MemoryRepository): ObserveMemoryVariableUseCase =
        ObserveMemoryVariableUseCaseImpl(memoryRepository)


    @Provides
    @Singleton
    fun providesLoginUseCase(): LoginUseCase = LoginUseCaseImpl()

    @Provides
    @Singleton
    fun providesLogoutUseCase(loggedInUserComponentManager: LoggedInUserComponentManager): LogoutUseCase =
        LogoutUseCaseImpl(loggedInUserComponentManager)
}