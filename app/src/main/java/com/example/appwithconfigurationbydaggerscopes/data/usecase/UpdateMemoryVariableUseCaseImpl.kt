package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import javax.inject.Inject

class UpdateMemoryVariableUseCaseImpl @Inject constructor(private val memoryRepository: MemoryRepository) :
    UpdateMemoryVariableUseCase {
    init {
        Log.w(
            "UpdateMemoryVariableUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(params: Int) {
        memoryRepository.saveVariable(params)
    }
}