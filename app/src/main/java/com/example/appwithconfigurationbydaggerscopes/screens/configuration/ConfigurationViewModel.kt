package com.example.appwithconfigurationbydaggerscopes.screens.configuration

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.SetupInitialValueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigurationViewModel @Inject constructor(private val setupInitialValueUseCase: SetupInitialValueUseCase) :
    ViewModel() {

    init {
        Log.w(
            "ConfigurationViewModel",
            "init ${hashCode()}"
        )
    }

    fun onValueConfirm(value: Int) {
        setupInitialValueUseCase.execute(value)
    }

    override fun onCleared() {
        Log.w(
            "ConfigurationViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }
}