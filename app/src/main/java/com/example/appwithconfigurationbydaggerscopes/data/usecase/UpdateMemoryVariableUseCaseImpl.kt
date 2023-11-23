package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateMemoryVariableUseCase
import javax.inject.Inject

class UpdateMemoryVariableUseCaseImpl @Inject constructor(private val memoryRepository: MemoryRepository) :
    UpdateMemoryVariableUseCase {
    override fun execute(params: Int) {
        memoryRepository.saveVariable(params)
    }
}