package com.example.appwithconfigurationbydaggerscopes.domain.repository

interface ActiveMemoryRepository {

    fun saveVariable(count: Int)

    fun readVariable(): Int

}