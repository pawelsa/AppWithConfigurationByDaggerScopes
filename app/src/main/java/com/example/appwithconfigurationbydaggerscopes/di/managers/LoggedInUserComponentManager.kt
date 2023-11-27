package com.example.appwithconfigurationbydaggerscopes.di.managers

import com.example.appwithconfigurationbydaggerscopes.di.components.LoggedInUserComponent
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class LoggedInUserComponentManager @Inject constructor(
    private val componentBuilder: Provider<LoggedInUserComponent.Builder>
) {

    var loggedInUserComponent: LoggedInUserComponent = componentBuilder.get().build()
        private set

    fun rebuildComponent() {
        loggedInUserComponent = componentBuilder.get().build()
    }
}