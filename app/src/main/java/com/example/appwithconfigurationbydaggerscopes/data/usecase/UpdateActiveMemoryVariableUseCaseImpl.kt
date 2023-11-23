package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.UpdateActiveMemoryVariableUseCase
import javax.inject.Inject

class UpdateActiveMemoryVariableUseCaseImpl @Inject constructor(private val activeMemoryRepository: ActiveMemoryRepository): UpdateActiveMemoryVariableUseCase {
    override fun execute(params: Int) {
        activeMemoryRepository.saveVariable(params)
    }
}