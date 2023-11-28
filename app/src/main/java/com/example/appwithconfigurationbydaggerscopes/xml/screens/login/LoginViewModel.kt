package com.example.appwithconfigurationbydaggerscopes.xml.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    init {
        Log.w(
            "LoginViewModel",
            "init ${hashCode()}"
        )
    }

    fun onLoginClick() {
        loginUseCase.execute()
    }

    override fun onCleared() {
        Log.w(
            "LoginViewModel",
            "onCleared ${hashCode()}"
        )
        super.onCleared()
    }

}