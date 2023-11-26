package com.example.appwithconfigurationbydaggerscopes.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ObserveMemoryVariableUseCase {
    fun execute(): Flow<Int>
}