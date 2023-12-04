package com.example.appwithconfigurationbydaggerscopes.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnlyMemoryViewModel @Inject constructor(
    observeMemoryVariableUseCase: ObserveMemoryVariableUseCase,
    private val updateMemoryVariableUseCase: UpdateMemoryVariableUseCase
) : ViewModel() {

    init {
        Log.w(
            "OnlyMemoryViewModel",
            "init ${hashCode()}"
        )
    }

    val memoryCount: Flow<Int> = observeMemoryVariableUseCase.execute()

    fun onUpdateMemoryVariable() = viewModelScope.launch {
        updateMemoryVariableUseCase.execute((memoryCount.first()) + 1)
    }

    override fun onCleared() {
        Log.w(
            "OnlyMemoryViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }
}