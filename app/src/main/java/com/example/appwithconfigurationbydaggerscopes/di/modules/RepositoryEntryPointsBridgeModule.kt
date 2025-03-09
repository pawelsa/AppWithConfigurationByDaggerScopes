package com.example.appwithconfigurationbydaggerscopes.di.modules

import android.util.Log
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

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryEntryPointsBridgeModule {
    @Provides
    internal fun provideMemoryRepository(
        loggedInUserComponentManager: LoggedInUserComponentManager
    ): MemoryRepository {
        Log.w(
            "RepositoryEntryPointsBridgeModule",
            "obtaining memory repository from entrypoints"
        )
        return EntryPoints
            .get(
                loggedInUserComponentManager.loggedInUserComponent,
                MemoryEntryPoint::class.java
            )
            .getMemoryRepository()
    }

    @Provides
    internal fun provideActiveMemoryRepository(
        loggedInUserComponentManager: LoggedInUserComponentManager
    ): ActiveMemoryRepository {
        Log.w(
            "RepositoryEntryPointsBridgeModule",
            "obtaining active memory repository from entrypoints"
        )
        return EntryPoints
            .get(
                loggedInUserComponentManager.loggedInUserComponent,
                ActiveMemoryEntryPoint::class.java
            )
            .getActiveMemoryRepository()
    }
}