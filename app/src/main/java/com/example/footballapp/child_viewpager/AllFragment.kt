package com.example.footballapp.child_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.footballapp.adapters.ListAdapter
import com.example.footballapp.databinding.FragmentAllBinding
import com.example.footballapp.respository.Extra
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.room.League
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listAdapter: ListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                TeamRepository(ApiClient.apiService),
                networkHelper = NetworkHelper(requireContext())
            )
        )[MainViewModel::class.java]
        lifecycleScope.launch {
            mainViewModel.fetchTeamsByLeague(Extra.getLeague1()!!.id).collect {
                when (it) {
                    is TeamResource.Success -> {
                        listAdapter = ListAdapter(requireContext(), it.body)
                        binding.listView.adapter = listAdapter
                    }
                }
            }
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(): MutableList<League> {
        val list =
            listOf("arg.1", "aus.1", "bra.1", "eng.1", "fra.1", "ger.1", "ita.1")
        return mutableListOf(
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

    }
}