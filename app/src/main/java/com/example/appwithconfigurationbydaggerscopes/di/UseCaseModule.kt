package com.example.appwithconfigurationbydaggerscopes.di

import com.example.appwithconfigurationbydaggerscopes.data.usecase.LoginUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.LogoutUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveActiveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.ObserveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.SetupBaseUrlUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.UpdateActiveMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.data.usecase.UpdateMemoryVariableUseCaseImpl
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupBaseUrlUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindsLoginUseCase(loginUseCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    abstract fun bindsLogoutUseCase(logoutUseCase: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    abstract fun bindsSetupBaseUrlUseCase(setupBaseUrlUseCase: SetupBaseUrlUseCaseImpl): SetupBaseUrlUseCase

    @Binds
    abstract fun bindsUpdateActiveMemoryVariableUseCase(updateActiveMemoryVariableUseCase: UpdateActiveMemoryVariableUseCaseImpl): UpdateActiveMemoryVariableUseCase

    @Binds
    abstract fun bindsUpdateMemoryVariableUseCase(updateMemoryVariableUseCase: UpdateMemoryVariableUseCaseImpl): UpdateMemoryVariableUseCase

    @Binds
    abstract fun bindsObserveActiveMemoryVariableUseCase(observeActiveMemoryVariableUseCase: ObserveActiveMemoryVariableUseCaseImpl): ObserveActiveMemoryVariableUseCase

    @Binds
    abstract fun bindsObserveMemoryVariableUseCase(observeMemoryVariableUseCase: ObserveMemoryVariableUseCaseImpl): ObserveMemoryVariableUseCase
}