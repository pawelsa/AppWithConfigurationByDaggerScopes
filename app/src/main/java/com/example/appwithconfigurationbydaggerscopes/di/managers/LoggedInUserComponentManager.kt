package com.example.appwithconfigurationbydaggerscopes.di.managers

import android.util.Log
import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class LoggedInUserComponentManager @Inject constructor(
    private val componentBuilder: Provider<LoggedInUserComponent.Builder>
) {

    init {
        Log.w(
            "LoggedInUserComponentManager",
            "init ${this.hashCode()}"
        )
    }

    var loggedInUserComponent: LoggedInUserComponent = componentBuilder.get().build()
        private set

    fun rebuildComponent() {
        Log.e(
            "LoggedInUserComponentManager",
            "rebuildComponent"
        )
        loggedInUserComponent = componentBuilder.get().build()
    }
}