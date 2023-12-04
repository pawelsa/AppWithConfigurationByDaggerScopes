package com.example.appwithconfigurationbydaggerscopes.ui.compose.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.HomeViewModel

@Composable
fun HomePage(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    Scaffold {
        Column(Modifier.padding(it)) {
            val memoryCount by viewModel.memoryCount.collectAsState(initial = 0)
            val activeMemoryCount by viewModel.activeMemoryCount.collectAsState(initial = 0)

            Row {
                Text(memoryCount.toString())
                ElevatedButton(onClick = { viewModel.onUpdateMemoryVariable() }) {
                    Text("Increase memory")
                }
            }
            Row {
                Text(activeMemoryCount.toString())
                ElevatedButton(onClick = { viewModel.onUpdateActiveMemoryVariable() }) {
                    Text("Increase active memory")
                }
            }
            ElevatedButton(onClick = {
                navController.navigate("onlyMemory")
            }) {
                Text("Open only memory page")
            }
            ElevatedButton(onClick = {
                navController.navigate("onlyActiveMemory")
            }) {
                Text("Open only active memory page")
            }
            ElevatedButton(onClick = {
                viewModel.onLogoutClick()
                navController.navigate("configuration") {
                    this.popUpTo("home") {
                        this.inclusive = true
                    }
                }
            }) {
                Text("Logout")
            }
        }
    }
}