package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCaseImpl @Inject constructor(private val loggedInUserComponentManager: LoggedInUserComponentManager) :
    LogoutUseCase {

    init {
        Log.w(
            "LogoutUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(): Boolean {
        loggedInUserComponentManager.clearComponent()
        return true
    }
}