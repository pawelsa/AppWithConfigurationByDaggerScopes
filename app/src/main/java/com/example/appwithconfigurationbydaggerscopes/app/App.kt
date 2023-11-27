package com.example.appwithconfigurationbydaggerscopes.app

import android.app.Application
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var loggedInUserComponentManager: LoggedInUserComponentManager
}