package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import javax.inject.Inject

class UpdateActiveMemoryVariableUseCaseImpl @Inject constructor(private val activeMemoryRepository: ActiveMemoryRepository): UpdateActiveMemoryVariableUseCase {
    init {
        Log.w(
            "UpdateActiveMemoryVariableUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(params: Int) {
        activeMemoryRepository.saveVariable(params)
    }
}