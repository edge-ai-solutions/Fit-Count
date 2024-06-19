package com.app.squatcounter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.squatcounter.R
import com.app.squatcounter.databinding.FragmentWelcomeBinding
import com.google.firebase.FirebaseApp

class WelcomeFragment : Fragment() {

    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        FirebaseApp.initializeApp(requireActivity())
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startBtn?.setOnClickListener { findNavController().navigate(R.id.action_welcomeFragment_to_userNameFragment) }
        binding?.leaderboardBtn?.setOnClickListener { findNavController().navigate(R.id.action_global_leaderBoardFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}