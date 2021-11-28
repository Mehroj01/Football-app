package com.example.footballapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.footballapp.R
import com.example.footballapp.adapters.HomeAdapter
import com.example.footballapp.databinding.FragmentHomeBinding
import com.example.footballapp.respository.Extra

import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.room.League
import com.example.footballapp.utils.MainModelResource
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var mainViewModel: MainViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                TeamRepository(ApiClient.apiService),
                networkHelper = NetworkHelper(requireContext())
            )
        )[MainViewModel::class.java]

        loadData()
        lifecycleScope.launch {
            mainViewModel.fetchMainModel().collect {
                when (it) {
                    is MainModelResource.Success -> {
                        Log.d("Heeeeey", "${it.body}")
                    }
                    is MainModelResource.Error -> {
                        Log.d("Heeeeey", "${it.message}")
                    }
                }
            }
        }

        homeAdapter =
            HomeAdapter(listener = object : HomeAdapter.OnItemClickListener {
                override fun onClick(league: League) {
                    val bundle = Bundle()
                    bundle.putSerializable("league", league)
                    Extra.setLeague1(league)
                    findNavController().navigate(R.id.action_homeFragment_to_teamFragment, bundle)
                }

            }, context = requireContext(), leagues = loadData())
        binding.rv.adapter = homeAdapter




        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(): MutableList<League> {
        val list =
            listOf("arg.1", "aus.1", "bra.1", "eng.1", "fra.1", "ger.1", "ita.1")
        val leagueList = mutableListOf(
            League(
                id = "arg.1",
                name = "Argentine Liga Profesional de Fútbol",
                ab = "Prim A",
                "https://a.espncdn.com/i/leaguelogos/soccer/500/1.png"
            ),
            League(
                id = "aus.1",
                name = "Australian A-League",
                ab = "A Lge",
                "https://a.espncdn.com/i/leaguelogos/soccer/500-dark/1308.png"
            ),
            League(
                id = "ned.1",
                name = "Dutch Eredivisie",
                ab = "Erediv",
                "https://a.espncdn.com/i/leaguelogos/soccer/500/11.png"
            ),
            League(
                id = "eng.1",
                name = "English Premier League",
                ab = "Prem",
                "https://a.espncdn.com/i/leaguelogos/soccer/500-dark/23.png"
            ),
            League(
                id = "fra.1",
                name = "French Ligue 1",
                ab = "Ligue 1",
                "https://a.espncdn.com/i/leaguelogos/soccer/500/9.png"
            ),
            League(
                id = "ger.1",
                name = "German Bundesliga",
                ab = "Bund",
                "https://a.espncdn.com/i/leaguelogos/soccer/500/10.png"
            ),
            League(
                id = "ita.1",
                name = "Italian Serie A",
                ab = "Serie A",
                "https://a.espncdn.com/i/leaguelogos/soccer/500-dark/12.png"
            )


        )
        return leagueList

    }
}