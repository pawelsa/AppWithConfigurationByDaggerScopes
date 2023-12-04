package com.example.appwithconfigurationbydaggerscopes.ui.compose.screens.only_memory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.OnlyMemoryViewModel

@Composable
fun OnlyMemoryPage(viewModel: OnlyMemoryViewModel = hiltViewModel()) {
    val count by viewModel.memoryCount.collectAsState(initial = 0)

    Scaffold {
        Column(Modifier.padding(it)) {
            Text(count.toString())
            ElevatedButton(onClick = viewModel::onUpdateMemoryVariable) {
                Text("Increase memory")
            }
        }
    }
}