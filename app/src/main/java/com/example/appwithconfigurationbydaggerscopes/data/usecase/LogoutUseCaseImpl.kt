package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCaseImpl @Inject constructor(private val loggedInUserComponentManager: LoggedInUserComponentManager) :
    LogoutUseCase {
    override fun execute(): Boolean {
        loggedInUserComponentManager.rebuildComponent()
        return true
    }
}