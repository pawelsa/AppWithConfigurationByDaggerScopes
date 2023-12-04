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

    private var _loggedInUserComponent: LoggedInUserComponent? = null

    val loggedInUserComponent: LoggedInUserComponent
        get() {
            var loggedInUserComponent = _loggedInUserComponent
            if (loggedInUserComponent == null) {
                loggedInUserComponent = componentBuilder.get().build()
                _loggedInUserComponent = loggedInUserComponent
            }
            return loggedInUserComponent
        }

    fun clearComponent() {
        Log.e(
            "LoggedInUserComponentManager",
            "rebuildComponent"
        )
        _loggedInUserComponent = null
    }
}