package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMemoryVariableUseCaseImpl @Inject constructor(private val memoryVariableRepository: MemoryRepository) :
    ObserveMemoryVariableUseCase {
    override fun execute(): Flow<Int> {
        return memoryVariableRepository.observeVariable()
    }
}