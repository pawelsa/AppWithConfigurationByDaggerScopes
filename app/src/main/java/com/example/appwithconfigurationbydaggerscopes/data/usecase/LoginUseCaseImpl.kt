package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase

class LoginUseCaseImpl : LoginUseCase {
    init {
        Log.w(
            "LoginUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(): Boolean {
        return true
    }
}