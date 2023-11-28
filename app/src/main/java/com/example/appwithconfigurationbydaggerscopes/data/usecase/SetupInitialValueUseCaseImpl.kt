package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupInitialValueUseCase
import javax.inject.Inject

class SetupInitialValueUseCaseImpl @Inject constructor(private val settings: Settings) : SetupInitialValueUseCase {
    init {
        Log.w(
            "SetupInitialValueUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(params: Int) {
        settings.variable = params
    }
}