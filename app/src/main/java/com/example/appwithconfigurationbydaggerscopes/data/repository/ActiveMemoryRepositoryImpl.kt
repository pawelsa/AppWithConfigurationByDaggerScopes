package com.example.appwithconfigurationbydaggerscopes.data.repository

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ActiveMemoryRepositoryImpl @Inject constructor() : ActiveMemoryRepository {
    init {
        Log.w(
            "ActiveMemoryRepository",
            "init ${this.hashCode()}"
        )
    }

    private var variable: Int = 0
    private val variableLiveData = MutableStateFlow(variable)

    override fun saveVariable(count: Int) {
        variable = count
        variableLiveData.value = count
    }

    override fun readVariable(): Int {
        return variable
    }

    override fun observeVariable(): Flow<Int> = variableLiveData
}