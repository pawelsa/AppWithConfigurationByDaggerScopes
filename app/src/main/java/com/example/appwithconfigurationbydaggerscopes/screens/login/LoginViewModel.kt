package com.example.appwithconfigurationbydaggerscopes.screens.login

import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun onLoginClick(){
        loginUseCase.execute()
    }

}