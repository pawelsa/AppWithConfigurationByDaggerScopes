package com.example.appwithconfigurationbydaggerscopes.ui.compose.screens.only_active_memory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.OnlyActiveMemoryViewModel

@Composable
fun OnlyActiveMemoryPage(viewModel: OnlyActiveMemoryViewModel = hiltViewModel()) {
    val count by viewModel.activeMemoryCount.collectAsState(initial = 0)

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(count.toString())
            ElevatedButton(onClick = viewModel::onUpdateActiveMemoryVariable) {
                Text("Increase active memory")
            }
        }
    }
}