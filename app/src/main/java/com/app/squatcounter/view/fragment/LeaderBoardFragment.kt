package com.app.squatcounter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.squatcounter.R
import com.app.squatcounter.view.adapter.recyclerview.LeaderBoardAdapter
import com.app.squatcounter.view.adapter.spinnerview.CustomAdapterRes
import com.app.squatcounter.view.adapter.spinnerview.ExercisesAdapter
import com.app.squatcounter.databinding.FragmentLeaderboardBinding
import com.app.squatcounter.databinding.ViewBtnHomeBinding
import com.app.squatcounter.model.ChallengeViewModel
import com.app.squatcounter.util.ChallengeEnum

class LeaderBoardFragment : Fragment() {

    var binding: FragmentLeaderboardBinding? = null
    var bindingHomeBtn: ViewBtnHomeBinding? = null

    private val sharedViewModel: ChallengeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLeaderboardBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        bindingHomeBtn = binding?.backContainer
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingHomeBtn?.homeBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_leaderBoardFragment_to_welcomeFragment)
        }
        val leaderBoardAdapter = LeaderBoardAdapter()
        binding?.apply {
            recyclerView.apply {
                adapter = leaderBoardAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                val dividerItemDecoration = DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
                dividerItemDecoration.setDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.layer_decorator
                    )!!
                )
                addItemDecoration(dividerItemDecoration)
            }
        }
        sharedViewModel.getLeaderBoardList()
        sharedViewModel.leaderBoardList.observe(viewLifecycleOwner) { item ->
            if(item!=null){
                leaderBoardAdapter.submitList(item)
                binding?.recyclerView?.visibility = View.VISIBLE
                binding?.tvNoData?.visibility = View.GONE
            }else{
                binding?.recyclerView?.visibility = View.GONE
                binding?.tvNoData?.visibility = View.VISIBLE
            }

        }
       /* val spinnerAdapter = ExercisesAdapter(
            requireContext(),
            CustomAdapterRes(
                R.layout.item_exercises_small,
                R.layout.item_header_exercises_spinner_small,
                R.layout.item_exercises_drop_down_small
            )
        )
        val exerciseSpinner = binding?.exerciseSpinner
        exerciseSpinner?.adapter = spinnerAdapter
        exerciseSpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item = parent?.adapter?.getItem(position)
                    if (item != null) {
                        val s = item as String
                        //sharedViewModel.setType(s)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //Ignore
                }
            }*/
        /*exerciseSpinner?.setSelection(
            ChallengeEnum.getAllExercises().indexOf(sharedViewModel.type.value) + 1
        )*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}