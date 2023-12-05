package com.example.appwithconfigurationbydaggerscopes.ui.xml.screens.home_page

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
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.appwithconfigurationbydaggerscopes.R
import com.example.appwithconfigurationbydaggerscopes.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.logged_in_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        val activeMemoryText = view.findViewById<TextView>(R.id.activeMemoryCountText)
        val memoryText = view.findViewById<TextView>(R.id.memoryCountText)
        val activeMemoryButton = view.findViewById<Button>(R.id.activeMemoryCountButton)
        val memoryButton = view.findViewById<Button>(R.id.memoryCountButton)
        val onlyMemoryButton = view.findViewById<Button>(R.id.openMemoryPageButton)
        val onlyActiveMemoryButton = view.findViewById<Button>(R.id.openActiveMemoryButton)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.activeMemoryCount.collect {
                    activeMemoryText.text = it.toString()
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.memoryCount.collect {
                    memoryText.text = it.toString()
                }
            }
        }

        activeMemoryButton.setOnClickListener {
            viewModel.onUpdateActiveMemoryVariable()
        }
        memoryButton.setOnClickListener {
            viewModel.onUpdateMemoryVariable()
        }
        onlyMemoryButton.setOnClickListener {
            findNavController().navigate(
                R.id.onlyMemoryFragment,
            )
        }
        onlyActiveMemoryButton.setOnClickListener {
            findNavController().navigate(
                R.id.onlyActiveMemoryFragment,
            )
        }
        logoutButton.setOnClickListener {
            viewModel.onLogoutClick()
            findNavController().navigate(
                R.id.configurationFragment,
                null,
                NavOptions.Builder().setPopUpTo(
                    R.id.homeFragment,
                    true
                ).build()
            )
        }
    }
}