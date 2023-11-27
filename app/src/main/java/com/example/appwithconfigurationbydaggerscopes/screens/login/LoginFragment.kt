package com.example.appwithconfigurationbydaggerscopes.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appwithconfigurationbydaggerscopes.R
import com.example.appwithconfigurationbydaggerscopes.di.managers.LoggedInUserComponentManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var loggedInUserComponentManager: LoggedInUserComponentManager

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        view.findViewById<Button>(R.id.to_home_button).setOnClickListener {
            viewModel.onLoginClick()
            findNavController().navigate(R.id.homeFragment)
        }
    }
}