package com.example.appwithconfigurationbydaggerscopes.ui.compose.screens.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.LoginViewModel

@Composable
fun LoginPage(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    Scaffold {
        ElevatedButton(
            modifier = Modifier.padding(it),
            onClick = {
                viewModel.onLoginClick()
                navController.navigate("home") {
                    this.popUpTo("configuration") {
                        this.inclusive = true
                    }
                }
            }) {
            Text("Open home")
        }
    }
}