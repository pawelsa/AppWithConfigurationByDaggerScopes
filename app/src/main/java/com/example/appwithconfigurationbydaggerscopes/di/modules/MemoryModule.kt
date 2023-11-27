package com.example.appwithconfigurationbydaggerscopes.di.modules

import com.example.appwithconfigurationbydaggerscopes.di.entry_point.ActiveMemoryEntryPoint
import com.example.appwithconfigurationbydaggerscopes.di.entry_point.MemoryEntryPoint
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemoryModule {

    @Provides
    @Singleton
    fun providesActiveMemoryRepository(loggedInUserComponentManager: LoggedInUserComponentManager): ActiveMemoryRepository {
        return EntryPoints.get(
            loggedInUserComponentManager.loggedInUserComponent,
            ActiveMemoryEntryPoint::class.java
        ).getActiveMemoryRepository()
    }

    @Provides
    @Singleton
    fun providesMemoryRepository(loggedInUserComponentManager: LoggedInUserComponentManager): MemoryRepository {
        return EntryPoints.get(
            loggedInUserComponentManager.loggedInUserComponent,
            MemoryEntryPoint::class.java
        ).getMemoryRepository()
    }
}