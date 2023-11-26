package com.example.appwithconfigurationbydaggerscopes.data.repository

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(private val settings: Settings) : MemoryRepository {
    init {
        Log.w(
            "MemoryRepository",
            "init ${this.hashCode()}"
        )
    }

    private val variableLiveData = MutableStateFlow(settings.variable)
    override fun saveVariable(count: Int) {
        settings.variable = count
        variableLiveData.value = count
    }

    override fun readVariable(): Int {
        return settings.variable
    }

    override fun observeVariable(): Flow<Int> = variableLiveData
}