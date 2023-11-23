package com.example.appwithconfigurationbydaggerscopes.di

import com.example.appwithconfigurationbydaggerscopes.data.repository.ActiveMemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.data.repository.MemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindActiveMemoryRepository(
        activeMemoryRepository: ActiveMemoryRepositoryImpl
    ): ActiveMemoryRepository

    @Binds
    abstract fun bindMemoryRepository(
        memoryRepository: MemoryRepositoryImpl
    ): MemoryRepository

}