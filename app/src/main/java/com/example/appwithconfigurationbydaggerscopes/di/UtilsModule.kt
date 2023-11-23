package com.example.appwithconfigurationbydaggerscopes.di

import android.content.Context
import android.content.SharedPreferences
import com.example.appwithconfigurationbydaggerscopes.data.SettingsImpl
import com.example.appwithconfigurationbydaggerscopes.domain.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun bindSettings(
        sharedPreferences: SharedPreferences
    ): Settings = SettingsImpl(sharedPreferences)

    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            "testDaggerScopes",
            Context.MODE_PRIVATE
        )

}