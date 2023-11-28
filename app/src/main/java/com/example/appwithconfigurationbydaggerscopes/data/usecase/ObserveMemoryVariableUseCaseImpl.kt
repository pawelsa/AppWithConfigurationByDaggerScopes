package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveMemoryVariableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveMemoryVariableUseCaseImpl @Inject constructor(
    private val memoryVariableRepository: MemoryRepository
) : ObserveMemoryVariableUseCase {

    init {
        Log.w(
            "ObserveMemoryVariableUseCaseImpl",
            "init ${this.hashCode()}"
        )
    }

    override fun execute(): Flow<Int> {
        return memoryVariableRepository.observeVariable()
    }
}