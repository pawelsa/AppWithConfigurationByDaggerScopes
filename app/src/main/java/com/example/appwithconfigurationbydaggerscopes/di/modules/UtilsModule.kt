package com.example.appwithconfigurationbydaggerscopes.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.appwithconfigurationbydaggerscopes.data.SettingsImpl
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun bindSettings(
        sharedPreferences: SharedPreferences
    ): Settings = SettingsImpl(sharedPreferences)

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            "testDaggerScopes",
            Context.MODE_PRIVATE
        )

}