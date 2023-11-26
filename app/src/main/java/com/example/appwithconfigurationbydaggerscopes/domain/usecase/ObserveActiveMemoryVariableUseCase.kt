package com.example.appwithconfigurationbydaggerscopes.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ObserveActiveMemoryVariableUseCase {
    fun execute(): Flow<Int>
}