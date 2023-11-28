package com.example.appwithconfigurationbydaggerscopes.di.modules

import com.example.appwithconfigurationbydaggerscopes.data.usecase.LoginUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.LogoutUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveActiveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.SetupInitialValueUseCaseImpl
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
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupInitialValueUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesSetupBaseUrlUseCase(settings: Settings): SetupInitialValueUseCase =
        SetupInitialValueUseCaseImpl(settings)

    @Provides
    @ViewModelScoped
    fun providesUpdateActiveMemoryVariableUseCase(activeMemoryRepository: ActiveMemoryRepository): UpdateActiveMemoryVariableUseCase =
        UpdateActiveMemoryVariableUseCaseImpl(activeMemoryRepository)

    @Provides
    @ViewModelScoped
    fun providesUpdateMemoryVariableUseCase(memoryRepository: MemoryRepository): UpdateMemoryVariableUseCase =
        UpdateMemoryVariableUseCaseImpl(memoryRepository)

    @Provides
    @ViewModelScoped
    fun providesObserveActiveMemoryVariableUseCase(activeMemoryRepository: ActiveMemoryRepository): ObserveActiveMemoryVariableUseCase =
        ObserveActiveMemoryVariableUseCaseImpl(activeMemoryRepository)

    @Provides
    @ViewModelScoped
    fun providesObserveMemoryVariableUseCase(memoryRepository: MemoryRepository): ObserveMemoryVariableUseCase =
        ObserveMemoryVariableUseCaseImpl(memoryRepository)


    @Provides
    @ViewModelScoped
    fun providesLoginUseCase(): LoginUseCase = LoginUseCaseImpl()

    @Provides
    @ViewModelScoped
    fun providesLogoutUseCase(loggedInUserComponentManager: LoggedInUserComponentManager): LogoutUseCase =
        LogoutUseCaseImpl(loggedInUserComponentManager)
}