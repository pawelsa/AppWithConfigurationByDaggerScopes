package com.example.appwithconfigurationbydaggerscopes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loggedInUserComponentManager: LoggedInUserComponentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}