package com.example.appwithconfigurationbydaggerscopes.data.repository

import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import javax.inject.Inject

class ActiveMemoryRepositoryImpl @Inject constructor(): ActiveMemoryRepository {
    private var variable: Int = 0

    override fun saveVariable(count: Int) {
        variable = count
    }

    override fun readVariable(): Int {
        return variable
    }
}