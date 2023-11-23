package com.example.appwithconfigurationbydaggerscopes.domain.repository

interface MemoryRepository {

    fun saveVariable(count: Int)

    fun readVariable(): Int

}