package com.example.appwithconfigurationbydaggerscopes.screens.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appwithconfigurationbydaggerscopes.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigurationFragment : Fragment() {
    private val viewModel: ConfigurationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_configuration,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        view.findViewById<Button>(R.id.toLoginButton).setOnClickListener {
            val editText = view.findViewById<EditText>(R.id.urlEditText)
            viewModel.onBaseUrlConfirm(editText.text.toString())
            val navController = findNavController()
            navController.navigate(R.id.loginFragment)
        }

    }
}