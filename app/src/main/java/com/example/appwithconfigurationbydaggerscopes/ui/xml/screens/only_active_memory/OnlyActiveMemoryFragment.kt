package com.example.appwithconfigurationbydaggerscopes.ui.xml.screens.only_active_memory

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
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.OnlyActiveMemoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnlyActiveMemoryFragment : Fragment() {
    private val viewModel: OnlyActiveMemoryViewModel by hiltNavGraphViewModels(R.id.logged_in_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_only_active_memory,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        val activeMemoryText = view.findViewById<TextView>(R.id.activeMemoryCountDetailsText)
        val activeMemoryButton = view.findViewById<Button>(R.id.activeMemoryCountDetailsButton)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.activeMemoryCount.collect {
                    activeMemoryText.text = it.toString()
                }
            }
        }
        activeMemoryButton.setOnClickListener {
            viewModel.onUpdateActiveMemoryVariable()
        }
    }
}