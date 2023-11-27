package com.example.appwithconfigurationbydaggerscopes.di.entry_point

import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@InstallIn(LoggedInUserComponent::class)
@EntryPoint
interface ActiveMemoryEntryPoint {

    fun getActiveMemoryRepository(): ActiveMemoryRepository

}

@InstallIn(LoggedInUserComponent::class)
@EntryPoint
interface MemoryEntryPoint {

    fun getMemoryRepository(): MemoryRepository
}