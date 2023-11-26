package com.example.appwithconfigurationbydaggerscopes.screens.home_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val updateActiveMemoryVariableUseCase: UpdateActiveMemoryVariableUseCase,
    private val updateMemoryVariableUseCase: UpdateMemoryVariableUseCase,
    observeActiveMemoryVariableUseCase: ObserveActiveMemoryVariableUseCase,
    observeMemoryVariableUseCase: ObserveMemoryVariableUseCase,
) : ViewModel() {

    init {
        Log.w(
            "HomeViewModel",
            "init ${hashCode()}"
        )
    }

    val memoryCount: Flow<Int> = observeMemoryVariableUseCase.execute()
    val activeMemoryCount: Flow<Int> = observeActiveMemoryVariableUseCase.execute()

    fun onUpdateActiveMemoryVariable() = viewModelScope.launch {
        updateActiveMemoryVariableUseCase.execute((activeMemoryCount.first()) + 1)
    }

    fun onUpdateMemoryVariable() = viewModelScope.launch {
        updateMemoryVariableUseCase.execute((memoryCount.first()) + 1)
    }

    fun onLogoutClick() {
        logoutUseCase.execute()
    }

    override fun onCleared() {
        Log.w(
            "HomeViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }

}