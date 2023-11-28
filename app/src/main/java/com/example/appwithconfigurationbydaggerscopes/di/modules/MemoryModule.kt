package com.example.appwithconfigurationbydaggerscopes.di.modules

import com.example.appwithconfigurationbydaggerscopes.data.repository.ActiveMemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.data.repository.MemoryRepositoryImpl
import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import com.example.appwithconfigurationbydaggerscopes.di.scopes.LoggedInUserScope
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import com.example.appwithconfigurationbydaggerscopes.domain.repository.ActiveMemoryRepository
import com.example.appwithconfigurationbydaggerscopes.domain.repository.MemoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(LoggedInUserComponent::class)
object MemoryModule {

    @Provides
    @LoggedInUserScope
    fun providesActiveMemoryRepository(): ActiveMemoryRepository = ActiveMemoryRepositoryImpl()

    @Provides
    @LoggedInUserScope
    fun providesMemoryRepository(settings: Settings): MemoryRepository = MemoryRepositoryImpl(settings)
}