package com.example.appwithconfigurationbydaggerscopes.ui.xml.screens.only_memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.appwithconfigurationbydaggerscopes.R
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.OnlyMemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnlyMemoryFragment : Fragment() {
    private val viewModel: OnlyMemoryViewModel by hiltNavGraphViewModels(R.id.logged_in_navigation)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_only_memory,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        val memoryText = view.findViewById<TextView>(R.id.memoryCountDetailsText)
        val memoryButton = view.findViewById<Button>(R.id.memoryCountDetailsButton)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.memoryCount.collect {
                    memoryText.text = it.toString()
                }
            }
        }
        memoryButton.setOnClickListener {
            viewModel.onUpdateMemoryVariable()
        }
    }
}