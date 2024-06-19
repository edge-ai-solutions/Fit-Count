package com.app.squatcounter.view.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.squatcounter.R
import com.app.squatcounter.databinding.FragmentUserNameBinding
import com.app.squatcounter.model.ChallengeViewModel
import com.app.squatcounter.util.SharedPref
import com.google.firebase.FirebaseApp

class UserNameFragment : Fragment() {

    var binding: FragmentUserNameBinding? = null
    private val sharedViewModel: ChallengeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentUserNameBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        FirebaseApp.initializeApp(requireContext())
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.backBtn?.setOnClickListener { findNavController().popBackStack() }
        binding?.etInputName?.doAfterTextChanged { text ->
            binding!!.nextBtn.isEnabled = !TextUtils.isEmpty(text) && text?.length!! > 2
        }
        binding?.etInputName?.setText(SharedPref.getInstance(activity?.application)?.getString("name").toString())
        binding?.nextBtn?.setOnClickListener {
            //sharedViewModel.setName(binding?.etInputName?.text.toString())
            SharedPref.getInstance(activity?.application)?.saveString("name",binding?.etInputName?.text.toString())
            findNavController().navigate(R.id.action_userNameFragment_to_challengeFragment)
            //findNavController().navigate(R.id.action_exercisesFragment_to_challengeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}