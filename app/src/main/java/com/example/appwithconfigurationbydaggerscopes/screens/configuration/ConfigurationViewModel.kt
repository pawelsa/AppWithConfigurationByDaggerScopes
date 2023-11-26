package com.example.appwithconfigurationbydaggerscopes.screens.configuration

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupBaseUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigurationViewModel @Inject constructor(private val setupBaseUrlUseCase: SetupBaseUrlUseCase) : ViewModel() {

    init {
        Log.w(
            "ConfigurationViewModel",
            "init ${hashCode()}"
        )
    }

    fun onBaseUrlConfirm(baseUrl: String) {
        setupBaseUrlUseCase.execute(baseUrl)
    }

    override fun onCleared() {
        Log.w(
            "ConfigurationViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }
}