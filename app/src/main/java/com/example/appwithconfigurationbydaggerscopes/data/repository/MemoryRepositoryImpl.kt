package com.example.appwithconfigurationbydaggerscopes.data.repository

import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(private val settings: Settings) : MemoryRepository {

    override fun saveVariable(count: Int) {
        settings.variable = count
    }

    override fun readVariable(): Int {
        return settings.variable
    }
}