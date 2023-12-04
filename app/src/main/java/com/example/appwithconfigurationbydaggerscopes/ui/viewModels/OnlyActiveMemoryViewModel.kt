package com.example.appwithconfigurationbydaggerscopes.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnlyActiveMemoryViewModel @Inject constructor(
    observeActiveMemoryVariableUseCase: ObserveActiveMemoryVariableUseCase,
    private val updateActiveMemoryVariableUseCase: UpdateActiveMemoryVariableUseCase
) : ViewModel() {

    init {
        Log.w(
            "OnlyActiveMemoryViewModel",
            "init ${hashCode()}"
        )
    }

    val activeMemoryCount: Flow<Int> = observeActiveMemoryVariableUseCase.execute()

    fun onUpdateActiveMemoryVariable() = viewModelScope.launch {
        updateActiveMemoryVariableUseCase.execute((activeMemoryCount.first()) + 1)
    }

    override fun onCleared() {
        Log.w(
            "OnlyActiveMemoryViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }
}