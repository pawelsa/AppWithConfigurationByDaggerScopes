package com.example.appwithconfigurationbydaggerscopes.screens.home_page

import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LogoutUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val updateActiveMemoryVariableUseCase: UpdateActiveMemoryVariableUseCase,
    private val updateMemoryVariableUseCase: UpdateMemoryVariableUseCase
) : ViewModel() {

    fun onUpdateActiveMemoryVariable(count: Int) {
        updateActiveMemoryVariableUseCase.execute(count)
    }

    fun onUpdateMemoryVariable(count: Int) {
        updateMemoryVariableUseCase.execute(count)
    }

    fun onLogoutClick() {
        logoutUseCase.execute()
    }

}