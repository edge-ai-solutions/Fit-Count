package com.app.squatcounter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.squatcounter.R
import com.app.squatcounter.databinding.FragmentResultBinding
import com.app.squatcounter.databinding.ViewBtnHomeBinding
import com.app.squatcounter.model.ChallengeViewModel
import com.app.squatcounter.util.ChallengeEnum
import com.app.squatcounter.util.SharedPref

class ResultFragment : Fragment() {

    private val sharedViewModel: ChallengeViewModel by activityViewModels()
    var binding: FragmentResultBinding? = null
    var homeBinding: ViewBtnHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        homeBinding = fragmentBinding.backContainer
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBinding?.homeBtn?.setOnClickListener {
            findNavController().navigate(
                R.id.action_resultFragment_to_welcomeFragment
            )
        }
        binding?.leaderboardBtn?.setOnClickListener { findNavController().navigate(R.id.action_global_leaderBoardFragment) }
        binding?.quantity?.text = SharedPref.getInstance(activity?.application)?.getString("score").toString()
        when (sharedViewModel.type.value) {
            ChallengeEnum.SQUAT.challengeName -> {
                binding?.exerciseName?.text = requireActivity().resources.getQuantityString(
                    R.plurals.squatting,
                    SharedPref.getInstance(activity?.application)?.getString("score")!!.toInt()
                )
            }
            ChallengeEnum.JUMP.challengeName -> {
                binding?.exerciseName?.text = requireActivity().resources.getQuantityString(
                    R.plurals.jumping,
                    SharedPref.getInstance(activity?.application)?.getString("score")!!.toInt()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}