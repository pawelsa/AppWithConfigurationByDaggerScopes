package com.example.appwithconfigurationbydaggerscopes.di.entry_point

import com.example.appwithconfigurationbydaggerscopes.data.repository.ActiveMemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.data.repository.MemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(LoggedInUserComponent::class)
interface ActiveMemoryEntryPoint {

    fun getActiveMemoryRepository(): ActiveMemoryRepositoryImpl

}

@EntryPoint
@InstallIn(LoggedInUserComponent::class)
interface MemoryEntryPoint {

    fun getMemoryRepository(): MemoryRepositoryImpl
}

