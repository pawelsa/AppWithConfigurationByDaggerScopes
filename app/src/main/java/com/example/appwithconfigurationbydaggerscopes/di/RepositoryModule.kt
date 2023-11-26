package com.example.appwithconfigurationbydaggerscopes.di

import com.example.appwithconfigurationbydaggerscopes.data.repository.ActiveMemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.data.repository.MemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindActiveMemoryRepository(): ActiveMemoryRepository = ActiveMemoryRepositoryImpl()

    @Provides
    @Singleton
    fun bindMemoryRepository(settings: Settings): MemoryRepository = MemoryRepositoryImpl(settings)

}