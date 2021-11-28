package com.example.footballapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.footballapp.R
import com.example.footballapp.adapters.AwayListViewAdapter
import com.example.footballapp.adapters.HomeListViewAdapter
import com.example.footballapp.databinding.FragmentHomeBinding
import com.example.footballapp.databinding.FragmentHomePlBinding
import com.example.footballapp.respository.Extra
import com.example.footballapp.respository.TeamRepository
import com.example.footballapp.retrofit.ApiClient
import com.example.footballapp.utils.NetworkHelper
import com.example.footballapp.utils.TeamResource
import com.example.footballapp.viewmodels.MainViewModel
import com.example.footballapp.viewmodels.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePlFragment : Fragment(R.layout.fragment_home_pl) {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var listAdapter:HomeListViewAdapter
    private var _binding: FragmentHomePlBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePlBinding.inflate(inflater, container, false)
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
                        listAdapter = HomeListViewAdapter(requireContext(), it.body)
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
}