package com.example.appwithconfigurationbydaggerscopes.data.usecase

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.usecase.ObserveActiveMemoryVariableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveActiveMemoryVariableUseCaseImpl @Inject constructor(private val activeMemoryVariableRepository: ActiveMemoryRepository) :
    ObserveActiveMemoryVariableUseCase {

    init {
        Log.w(
            "ObserveActiveMemoryVariableUseCase",
            "init ${hashCode()}"
        )
    }

    override fun execute(): Flow<Int> {
        return activeMemoryVariableRepository.observeVariable()
    }
}