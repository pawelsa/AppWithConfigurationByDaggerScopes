package com.example.appwithconfigurationbydaggerscopes.di.entry_point

import com.example.appwithconfigurationbydaggerscopes.data.repository.ActiveMemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.data.repository.MemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(LoggedInUserComponent::class)
@EntryPoint
interface ActiveMemoryEntryPoint {

    fun getActiveMemoryRepository(): ActiveMemoryRepositoryImpl

}

@InstallIn(LoggedInUserComponent::class)
@EntryPoint
interface MemoryEntryPoint {

    fun getMemoryRepository(): MemoryRepositoryImpl
}

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryEntryBridge {
    @Provides
    internal fun provideMemoryRepository(
        loggedInUserComponentManager: LoggedInUserComponentManager
    ): MemoryRepository {
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
        return EntryPoints
            .get(
                loggedInUserComponentManager.loggedInUserComponent,
                ActiveMemoryEntryPoint::class.java
            )
            .getActiveMemoryRepository()
    }
}