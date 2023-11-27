package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.usecase.LoginUseCase

class LoginUseCaseImpl : LoginUseCase {
    override fun execute(): Boolean {
        return true
    }
}