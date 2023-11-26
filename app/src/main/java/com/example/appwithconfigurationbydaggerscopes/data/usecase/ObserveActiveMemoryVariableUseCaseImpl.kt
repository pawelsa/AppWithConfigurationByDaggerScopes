package com.example.appwithconfigurationbydaggerscopes.data.usecase

import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveActiveMemoryVariableUseCaseImpl @Inject constructor(private val activeMemoryVariableRepository: ActiveMemoryRepository) :
    ObserveActiveMemoryVariableUseCase {
    override fun execute(): Flow<Int> {
        return activeMemoryVariableRepository.observeVariable()
    }
}