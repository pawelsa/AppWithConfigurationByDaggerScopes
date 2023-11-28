package com.example.appwithconfigurationbydaggerscopes.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.appwithconfigurationbydaggerscopes.compose.screens.configuration.ConfigurationPage
import com.example.appwithconfigurationbydaggerscopes.compose.screens.home.HomePage
import com.example.appwithconfigurationbydaggerscopes.compose.screens.login.LoginPage
import com.example.appwithconfigurationbydaggerscopes.compose.ui.theme.AppWithConfigurationByDaggerScopesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppWithConfigurationByDaggerScopesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "configuration"
                ) {
                    composable("configuration") {
                        ConfigurationPage(
                            navController = navController
                        )
                    }
                    composable("login") {
                        LoginPage(
                            navController = navController
                        )
                    }
                    navigation(
                        startDestination = "home",
                        route = "loggedInUser"
                    ) {
                        composable("home") {
                            HomePage(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}
