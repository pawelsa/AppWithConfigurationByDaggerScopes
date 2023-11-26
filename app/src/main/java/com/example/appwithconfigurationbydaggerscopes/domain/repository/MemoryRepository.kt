package com.example.appwithconfigurationbydaggerscopes.domain.repository

import kotlinx.coroutines.flow.Flow

interface MemoryRepository {

    fun saveVariable(count: Int)

    fun readVariable(): Int
    fun observeVariable(): Flow<Int>

}