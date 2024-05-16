package com.example.appwithconfigurationbydaggerscopes.ui.compose.screens.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.ConfigurationViewModel

@Composable
fun ConfigurationPage(viewModel: ConfigurationViewModel = hiltViewModel(), navController: NavController) {
    Scaffold {
        Column(
            Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var value by remember { mutableStateOf("") }
            TextField(
                value = value,
                onValueChange = { textValue ->
                    value = textValue
                },
                label = {
                    Text("Initial value")
                }
            )
            ElevatedButton(onClick = {
                if (value.isNotBlank()) {
                    viewModel.onValueConfirm(value.toInt())
                }
                navController.navigate("login")
            }) {
                Text(text = "Open login")
            }
        }
    }
}