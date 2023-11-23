package com.example.appwithconfigurationbydaggerscopes.screens.configuration

import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupBaseUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigurationViewModel @Inject constructor(private val setupBaseUrlUseCase: SetupBaseUrlUseCase) : ViewModel(){

    fun onBaseUrlConfirm(baseUrl: String){
        setupBaseUrlUseCase.execute(baseUrl)
    }
}