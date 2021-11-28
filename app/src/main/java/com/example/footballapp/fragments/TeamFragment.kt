package com.example.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballapp.R
import com.example.footballapp.databinding.FragmentTeamBinding
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.room.League
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory
import com.example.footballapp.viewpager.VAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.PI

class TeamFragment : Fragment(R.layout.fragment_team) {
    private lateinit var vAdapter: VAdapter
    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentTeamBinding? = null
    private var league: League? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            league = it.getSerializable("league") as League

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        vAdapter = VAdapter(parentFragmentManager, lifecycle)
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                TeamRepository(ApiClient.apiService),
                networkHelper = NetworkHelper(requireContext())
            )
        )[MainViewModel::class.java]

        binding.apply {
            Picasso.get().load(league!!.image).into(teamImage)
            teamName.text = league!!.name

            viewpager.adapter = vAdapter
            TabLayoutMediator(tab, viewpager) { tab, pos ->
                when (pos) {
                    0 -> {
                        tab.text = "Table"
                    }
                    1 -> {
                        tab.text = "Matches"
                    }
                }
            }.attach()

            lifecycleScope.launch {
                mainViewModel.fetchTeamsByLeague(league!!.id).collect {
                    when (it) {
                        is TeamResource.Loading -> {

                        }
                        is TeamResource.Success -> {

                        }
                    }
                }
            }

            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        return binding.root
    }
}