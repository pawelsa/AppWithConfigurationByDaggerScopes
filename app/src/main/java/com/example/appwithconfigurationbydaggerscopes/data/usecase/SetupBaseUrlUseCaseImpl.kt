package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupBaseUrlUseCase
import javax.inject.Inject

class SetupBaseUrlUseCaseImpl @Inject constructor(private val settings: Settings): SetupBaseUrlUseCase {
    override fun execute(params: String) {
        settings.baseUrl = params
    }
}